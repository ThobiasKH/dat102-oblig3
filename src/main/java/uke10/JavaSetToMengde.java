package uke10;

import java.util.HashSet;
import java.util.Set;

public class JavaSetToMengde<T> implements MengdeADT<T> {
    private Set<T> set = new HashSet<>();

    @Override
    public boolean erTom() {
        return set.isEmpty();
    }

    @Override
    public boolean inneholder(T element) {
        return set.contains(element);
    }

    @Override
    public void leggTil(T element) {
        set.add(element);
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        for (T element : set) {
            if (!annenMengde.inneholder(element)) return false;
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        for (T element : set) {
            if (annenMengde.inneholder(element)) return false;
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> snittMengde = new JavaSetToMengde<>();
        for (T element : set) {
            if (annenMengde.inneholder(element)) {
                snittMengde.leggTil(element);
            }
        }
        return snittMengde;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> unionMengde = new JavaSetToMengde<>();
        for (T element : set) {
            unionMengde.leggTil(element);
        }
        for (T element : annenMengde.tilTabell()) {
            if (!unionMengde.inneholder(element)) unionMengde.leggTil(element);
        }
        return unionMengde;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> diffMengde = new JavaSetToMengde<>();
        for (T element : set) {
            if (!annenMengde.inneholder(element)) {
                diffMengde.leggTil(element);
            }
        }
        return diffMengde;
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        for (T element : annenMengde.tilTabell()) {
            leggTil(element);
        }
    }

    @Override
    public T fjern(T element) {
        return set.remove(element) ? element : null;
    }


    @Override
    @SuppressWarnings("unchecked")
    public T[] tilTabell() {
        return (T[]) set.toArray();
    }

    @Override
    public int antallElementer() {
        return set.size();
    }
}
