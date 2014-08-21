/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrudPanels;

import CrudPanels.exceptions.NonexistentEntityException;
import CrudPanels.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author moez
 */
public class ChanteurJpaController implements Serializable {

    public ChanteurJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Chanteur chanteur) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(chanteur);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findChanteur(chanteur.getNomChanteur()) != null) {
                throw new PreexistingEntityException("Chanteur " + chanteur + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Chanteur chanteur) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            chanteur = em.merge(chanteur);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = chanteur.getNomChanteur();
                if (findChanteur(id) == null) {
                    throw new NonexistentEntityException("The chanteur with id " + id + " no longer exists.");
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
            Chanteur chanteur;
            try {
                chanteur = em.getReference(Chanteur.class, id);
                chanteur.getNomChanteur();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chanteur with id " + id + " no longer exists.", enfe);
            }
            em.remove(chanteur);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Chanteur> findChanteurEntities() {
        return findChanteurEntities(true, -1, -1);
    }

    public List<Chanteur> findChanteurEntities(int maxResults, int firstResult) {
        return findChanteurEntities(false, maxResults, firstResult);
    }

    private List<Chanteur> findChanteurEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Chanteur.class));
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

    public Chanteur findChanteur(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Chanteur.class, id);
        } finally {
            em.close();
        }
    }

    public int getChanteurCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Chanteur> rt = cq.from(Chanteur.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
