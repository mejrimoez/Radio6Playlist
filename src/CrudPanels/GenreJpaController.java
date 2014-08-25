/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrudPanels;

import CrudPanels.exceptions.NonexistentEntityException;
import CrudPanels.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author moez
 */
public class GenreJpaController implements Serializable {

    public GenreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genre genre) throws PreexistingEntityException, Exception {
        if (genre.getChansons() == null) {
            genre.setChansons(new ArrayList<Chanson>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Chanson> attachedChansons = new ArrayList<>();
            for (Chanson chansonsChansonToAttach : genre.getChansons()) {
                chansonsChansonToAttach = em.getReference(chansonsChansonToAttach.getClass(), chansonsChansonToAttach.getNumChanson());
                attachedChansons.add(chansonsChansonToAttach);
            }
            genre.setChansons(attachedChansons);
            em.persist(genre);
            for (Chanson chansonsChanson : genre.getChansons()) {
                chansonsChanson.getGenres().add(genre);
                chansonsChanson = em.merge(chansonsChanson);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGenre(genre.getNomGenre()) != null) {
                throw new PreexistingEntityException("Genre " + genre + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genre genre) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genre persistentGenre = em.find(Genre.class, genre.getNomGenre());
            List<Chanson> chansonsOld = persistentGenre.getChansons();
            List<Chanson> chansonsNew = genre.getChansons();
            List<Chanson> attachedChansonsNew = new ArrayList<>();
            for (Chanson chansonsNewChansonToAttach : chansonsNew) {
                chansonsNewChansonToAttach = em.getReference(chansonsNewChansonToAttach.getClass(), chansonsNewChansonToAttach.getNumChanson());
                attachedChansonsNew.add(chansonsNewChansonToAttach);
            }
            chansonsNew = attachedChansonsNew;
            genre.setChansons(chansonsNew);
            genre = em.merge(genre);
            for (Chanson chansonsOldChanson : chansonsOld) {
                if (!chansonsNew.contains(chansonsOldChanson)) {
                    chansonsOldChanson.getGenres().remove(genre);
                    chansonsOldChanson = em.merge(chansonsOldChanson);
                }
            }
            for (Chanson chansonsNewChanson : chansonsNew) {
                if (!chansonsOld.contains(chansonsNewChanson)) {
                    chansonsNewChanson.getGenres().add(genre);
                    chansonsNewChanson = em.merge(chansonsNewChanson);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = genre.getNomGenre();
                if (findGenre(id) == null) {
                    throw new NonexistentEntityException("The genre with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genre genre;
            try {
                genre = em.getReference(Genre.class, id);
                genre.getNomGenre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genre with id " + id + " no longer exists.", enfe);
            }
            Collection<Chanson> chansons = genre.getChansons();
            for (Chanson chansonsChanson : chansons) {
                chansonsChanson.getGenres().remove(genre);
                chansonsChanson = em.merge(chansonsChanson);
            }
            em.remove(genre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Genre> findGenreEntities() {
        return findGenreEntities(true, -1, -1);
    }

    public List<Genre> findGenreEntities(int maxResults, int firstResult) {
        return findGenreEntities(false, maxResults, firstResult);
    }

    private List<Genre> findGenreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genre.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Genre findGenre(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genre.class, id);
        } finally {
            em.close();
        }
    }

    public int getGenreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genre> rt = cq.from(Genre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
