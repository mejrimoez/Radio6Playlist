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
public class UtilisateurJpaController implements Serializable {

    public UtilisateurJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Utilisateur utilisateur) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(utilisateur);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUtilisateur(utilisateur.getLogin()) != null) {
                throw new PreexistingEntityException("Utilisateur " + utilisateur + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Utilisateur utilisateur) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            utilisateur = em.merge(utilisateur);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = utilisateur.getLogin();
                if (findUtilisateur(id) == null) {
                    throw new NonexistentEntityException("The utilisateur with id " + id + " no longer exists.");
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
            Utilisateur utilisateur;
            try {
                utilisateur = em.getReference(Utilisateur.class, id);
                utilisateur.getLogin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The utilisateur with id " + id + " no longer exists.", enfe);
            }
            em.remove(utilisateur);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Utilisateur> findUtilisateurEntities() {
        return findUtilisateurEntities(true, -1, -1);
    }

    public List<Utilisateur> findUtilisateurEntities(int maxResults, int firstResult) {
        return findUtilisateurEntities(false, maxResults, firstResult);
    }

    private List<Utilisateur> findUtilisateurEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Utilisateur.class));
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

    public Utilisateur findUtilisateur(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Utilisateur.class, id);
        } finally {
            em.close();
        }
    }

    public int getUtilisateurCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Utilisateur> rt = cq.from(Utilisateur.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
