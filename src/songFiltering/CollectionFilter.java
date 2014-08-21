/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package songFiltering;

import CrudPanels.Chanson;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author moez
 */
public class CollectionFilter implements Serializable {

    private final Hashtable<String, Object> allFilterCriteria = new Hashtable<>();

    public void addFilterCriteria(String name, FilterCriteria filter) {
        allFilterCriteria.put(name, filter);
    }

    public void removeFilterCriteria(String name) {
        allFilterCriteria.remove(name);
    }

    public void filter(List collection) {
        if (collection != null) {
            Iterator iter = collection.iterator();
            while (iter.hasNext()) {
                Chanson o = (Chanson) iter.next();
                if (!passesAllCritertia(o)) {
                    iter.remove();
                }
            }
        }
    }

    public List filterCopy(List inputCollection) {
        List outputCollection = null;
        try {
            outputCollection = (List) createObjectSameClass(inputCollection);
            outputCollection.addAll(inputCollection);

            Iterator iter = outputCollection.iterator();
            while (iter.hasNext()) {
                Chanson o = (Chanson) iter.next();
                if (!passesAllCritertia(o)) {
                    iter.remove();
                }
            }
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
        return outputCollection;
    }

    private boolean passesAllCritertia(Chanson o) {
        for (String key : allFilterCriteria.keySet()) {
            FilterCriteria filter = (FilterCriteria) allFilterCriteria.get(key);
            if (!filter.passes(o)) {
                return false;
            }
        }
        return true;
    }

    public Object createObjectSameClass(Object object) {

        Class[] NO_ARGS = new Class[0];
        Object sameClassObject = null;
        try {
            if (object != null) {
                Constructor constructor = object.getClass().getConstructor(NO_ARGS);
                sameClassObject = constructor.newInstance((Object) NO_ARGS);
            }
        } catch (IllegalAccessException e) {
            //@todo do something
        } catch (NoSuchMethodException e) {
            //@todo do something
        } catch (InstantiationException e) {
            //@todo do something
        } catch (Exception e) {
            //@todo do something
        }
        return sameClassObject;
    }
}
