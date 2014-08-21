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
public class SymboleJpaController implements Serializable {

    public SymboleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Symbole symbole) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(symbole);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSymbole(symbole.getNomSymbole()) != null) {
                throw new PreexistingEntityException("Symbole " + symbole + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Symbole symbole) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            symbole = em.merge(symbole);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = symbole.getNomSymbole();
                if (findSymbole(id) == null) {
                    throw new NonexistentEntityException("The symbole with id " + id + " no longer exists.");
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
            Symbole symbole;
            try {
                symbole = em.getReference(Symbole.class, id);
                symbole.getNomSymbole();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The symbole with id " + id + " no longer exists.", enfe);
            }
            em.remove(symbole);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Symbole> findSymboleEntities() {
        return findSymboleEntities(true, -1, -1);
    }

    public List<Symbole> findSymboleEntities(int maxResults, int firstResult) {
        return findSymboleEntities(false, maxResults, firstResult);
    }

    private List<Symbole> findSymboleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Symbole.class));
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

    public Symbole findSymbole(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Symbole.class, id);
        } finally {
            em.close();
        }
    }

    public int getSymboleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Symbole> rt = cq.from(Symbole.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
