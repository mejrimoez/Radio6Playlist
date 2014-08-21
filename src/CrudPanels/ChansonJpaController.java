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
public class ChansonJpaController implements Serializable {

    public ChansonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Chanson chanson) throws PreexistingEntityException, Exception {
        if (chanson.getGenres() == null) {
            chanson.setGenres(new ArrayList<Genre>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Genre> attachedGenres = new ArrayList<Genre>();
            for (Genre genresGenreToAttach : chanson.getGenres()) {
                genresGenreToAttach = em.getReference(genresGenreToAttach.getClass(), genresGenreToAttach.getNomGenre());
                attachedGenres.add(genresGenreToAttach);
            }
            chanson.setGenres(attachedGenres);
            em.persist(chanson);
            for (Genre genresGenre : chanson.getGenres()) {
                genresGenre.getChansons().add(chanson);
                genresGenre = em.merge(genresGenre);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findChanson(chanson.getNumChanson()) != null) {
                throw new PreexistingEntityException("Chanson " + chanson + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Chanson chanson) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Chanson persistentChanson = em.find(Chanson.class, chanson.getNumChanson());
            Collection<Genre> genresOld = persistentChanson.getGenres();
            Collection<Genre> genresNew = chanson.getGenres();
            Collection<Genre> attachedGenresNew = new ArrayList<Genre>();
            for (Genre genresNewGenreToAttach : genresNew) {
                genresNewGenreToAttach = em.getReference(genresNewGenreToAttach.getClass(), genresNewGenreToAttach.getNomGenre());
                attachedGenresNew.add(genresNewGenreToAttach);
            }
            genresNew = attachedGenresNew;
            chanson.setGenres(genresNew);
            chanson = em.merge(chanson);
            for (Genre genresOldGenre : genresOld) {
                if (!genresNew.contains(genresOldGenre)) {
                    genresOldGenre.getChansons().remove(chanson);
                    genresOldGenre = em.merge(genresOldGenre);
                }
            }
            for (Genre genresNewGenre : genresNew) {
                if (!genresOld.contains(genresNewGenre)) {
                    genresNewGenre.getChansons().add(chanson);
                    genresNewGenre = em.merge(genresNewGenre);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = chanson.getNumChanson();
                if (findChanson(id) == null) {
                    throw new NonexistentEntityException("The chanson with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Chanson chanson;
            try {
                chanson = em.getReference(Chanson.class, id);
                chanson.getNumChanson();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chanson with id " + id + " no longer exists.", enfe);
            }
            Collection<Genre> genres = chanson.getGenres();
            for (Genre genresGenre : genres) {
                genresGenre.getChansons().remove(chanson);
                genresGenre = em.merge(genresGenre);
            }
            em.remove(chanson);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Chanson> findChansonEntities() {
        return findChansonEntities(true, -1, -1);
    }

    public List<Chanson> findChansonEntities(int maxResults, int firstResult) {
        return findChansonEntities(false, maxResults, firstResult);
    }

    private List<Chanson> findChansonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Chanson.class));
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

    public Chanson findChanson(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Chanson.class, id);
        } finally {
            em.close();
        }
    }

    public int getChansonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Chanson> rt = cq.from(Chanson.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
