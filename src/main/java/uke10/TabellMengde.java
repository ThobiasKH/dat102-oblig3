package uke10;
import java.util.Arrays;

public class TabellMengde<T> implements MengdeADT<T> {
    private T[] tab;
    private int size;

    @SuppressWarnings("unchecked")
    public TabellMengde(int capacity) {
        tab = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public boolean erTom() {
        return size == 0;
    }

    @Override
    public boolean inneholder(T element) {
        for (int i = 0; i < size; i++) {
            if (tab[i].equals(element)) return true;
        }
        return false;
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        for (int i = 0; i < size; i++) {
            if (!annenMengde.inneholder(tab[i])) return false;
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        for (int i = 0; i < size; i++) {
            if (annenMengde.inneholder(tab[i])) return false;
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        TabellMengde<T> snittMengde = new TabellMengde<>(size);
        for (int i = 0; i < size; i++) {
            if (annenMengde.inneholder(tab[i])) {
                snittMengde.leggTil(tab[i]);
            }
        }
        return snittMengde;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        TabellMengde<T> unionMengde = new TabellMengde<>(size + annenMengde.antallElementer());
        for (int i = 0; i < size; i++) {
            unionMengde.leggTil(tab[i]);
        }
        for (T element : annenMengde.tilTabell()) {
            if (!unionMengde.inneholder(element)) unionMengde.leggTil(element);
        }
        return unionMengde;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        TabellMengde<T> diffMengde = new TabellMengde<>(size);
        for (int i = 0; i < size; i++) {
            if (!annenMengde.inneholder(tab[i])) {
                diffMengde.leggTil(tab[i]);
            }
        }
        return diffMengde;
    }

    @Override
    public void leggTil(T element) {
        if (!inneholder(element)) {
            if (size == tab.length) {
                tab = Arrays.copyOf(tab, size * 2);
            }
            tab[size++] = element;
        }
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        for (T element : annenMengde.tilTabell()) {
            if (!inneholder(element)) leggTil(element);
        }
    }

    @Override
    public T fjern(T element) {
        for (int i = 0; i < size; i++) {
            if (tab[i].equals(element)) {
                T removed = tab[i];
                tab[i] = tab[--size];
                tab[size] = null;
                return removed;
            }
        }
        return null;
    }

    @Override
    public T[] tilTabell() {
        return Arrays.copyOf(tab, size);
    }

    @Override
    public int antallElementer() {
        return size;
    }
}
