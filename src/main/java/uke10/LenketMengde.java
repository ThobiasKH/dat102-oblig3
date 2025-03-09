package uke10;

public class LenketMengde<T> implements MengdeADT<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }
    
    private Node<T> head;
    private int size;

    @Override
    public boolean erTom() {
        return size == 0;
    }

    @Override
    public boolean inneholder(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(element)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        Node<T> current = head;
        while (current != null) {
            if (!annenMengde.inneholder(current.data)) return false;
            current = current.next;
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        Node<T> current = head;
        while (current != null) {
            if (annenMengde.inneholder(current.data)) return false;
            current = current.next;
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        LenketMengde<T> snittMengde = new LenketMengde<>();
        Node<T> current = head;
        while (current != null) {
            if (annenMengde.inneholder(current.data)) {
                snittMengde.leggTil(current.data);
            }
            current = current.next;
        }
        return snittMengde;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        LenketMengde<T> unionMengde = new LenketMengde<>();
        Node<T> current = head;
        while (current != null) {
            unionMengde.leggTil(current.data);
            current = current.next;
        }
        for (T element : annenMengde.tilTabell()) {
            if (!unionMengde.inneholder(element)) unionMengde.leggTil(element);
        }
        return unionMengde;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        LenketMengde<T> diffMengde = new LenketMengde<>();
        Node<T> current = head;
        while (current != null) {
            if (!annenMengde.inneholder(current.data)) {
                diffMengde.leggTil(current.data);
            }
            current = current.next;
        }
        return diffMengde;
    }

    @Override
    public void leggTil(T element) {
        if (!inneholder(element)) {
            Node<T> newNode = new Node<>(element);
            newNode.next = head;
            head = newNode;
            size++;
        }
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        for (T element : annenMengde.tilTabell()) {
            leggTil(element);
        }
    }

    @Override
    public T fjern(T element) {
        if (head == null) return null;
        if (head.data.equals(element)) {
            T removed = head.data;
            head = head.next;
            size--;
            return removed;
        }
        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(element)) {
            current = current.next;
        }
        if (current.next != null) {
            T removed = current.next.data;
            current.next = current.next.next;
            size--;
            return removed;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] tilTabell() {
        T[] array = (T[]) new Object[size];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public int antallElementer() {
        return size;
    }
}
