//package uke10;
//
//public class Main {
//    public static void main(String[] args) {
//        int size = 100000;  // Adjust the size as needed for testing
//        // Create instances of each set implementation
//        TabellMengde<Integer> tabellMengde = new TabellMengde<>(size);
//        LenketMengde<Integer> lenketMengde = new LenketMengde<>();
//        JavaSetToMengde<Integer> hashSetMengde = new JavaSetToMengde<>();
//        
//        // Add some elements to each set
//        for (int i = 0; i < size; i++) {
//            tabellMengde.leggTil(i);
//            lenketMengde.leggTil(i);
//            hashSetMengde.leggTil(i);
//        }
//        
//        // Compare times for "inneholder" method
//        compareMethodTime("inneholder", size, tabellMengde, lenketMengde, hashSetMengde);
//        
//        // Compare times for "erDelmengdeAv" method
//        compareMethodTime("erDelmengdeAv", size, tabellMengde, lenketMengde, hashSetMengde);
//        
//        // Compare times for "erLik" method
//        compareMethodTime("erLik", size, tabellMengde, lenketMengde, hashSetMengde);
//        
//        // Compare times for "union" method
//        compareMethodTime("union", size, tabellMengde, lenketMengde, hashSetMengde);
//        
//        // Compare times for "fjern" method
//        compareMethodTime("fjern", size, tabellMengde, lenketMengde, hashSetMengde);
//    }
//    
//    private static void compareMethodTime(String methodName, int size, 
//                                          TabellMengde<Integer> tabellMengde, 
//                                          LenketMengde<Integer> lenketMengde, 
//                                          JavaSetToMengde<Integer> hashSetMengde) {
//        System.out.println("Comparing times for " + methodName);
//        
//        long startTime, endTime;
//        
//        // TabellMengde (Array-based set)
//        startTime = System.nanoTime();
//        runMethod(methodName, tabellMengde, size);
//        endTime = System.nanoTime();
//        System.out.println("TabellMengde " + methodName + ": " + (endTime - startTime) + " ns");
//        
//        // LenketMengde (Linked list-based set)
//        startTime = System.nanoTime();
//        runMethod(methodName, lenketMengde, size);
//        endTime = System.nanoTime();
//        System.out.println("LenketMengde " + methodName + ": " + (endTime - startTime) + " ns");
//        
//        // HashSetMengde (HashSet-based set)
//        startTime = System.nanoTime();
//        runMethod(methodName, hashSetMengde, size);
//        endTime = System.nanoTime();
//        System.out.println("HashSetMengde " + methodName + ": " + (endTime - startTime) + " ns");
//        
//        System.out.println();
//    }
//
//    @SuppressWarnings("unchecked")
//    private static void runMethod(String methodName, Object set, int size) {
//        if (set instanceof MengdeADT<?>) {
//            switch (methodName) {
//                case "inneholder":
//                    for (int i = 0; i < size; i++) {
//                        if (!((MengdeADT<Integer>) set).inneholder(i)) {
//                            System.out.println("Element " + i + " not found!");
//                        }
//                    }
//                    break;
//                case "erDelmengdeAv":
//                    MengdeADT<Integer> otherSet = new JavaSetToMengde<>();
//                    for (int i = 0; i < size / 2; i++) {
//                        otherSet.leggTil(i);
//                    }
//                    ((MengdeADT<Integer>) set).erDelmengdeAv(otherSet);
//                    break;
//                case "erLik":
//                    MengdeADT<Integer> anotherSet = new JavaSetToMengde<>();
//                    for (int i = 0; i < size; i++) {
//                        anotherSet.leggTil(i);
//                    }
//                    ((MengdeADT<Integer>) set).erLik(anotherSet);
//                    break;
//                case "union":
//                    MengdeADT<Integer> unionSet = new JavaSetToMengde<>();
//                    for (int i = 0; i < size; i++) {
//                        unionSet.leggTil(i);
//                    }
//                    ((MengdeADT<Integer>) set).union(unionSet);
//                    break;
//                case "fjern":
//                    for (int i = 0; i < size; i++) {
//                        ((MengdeADT<Integer>) set).fjern(i);
//                    }
//                    break;
//                default:
//                    break;
//            }
//        } else {
//            System.out.println("The provided set is not of type MengdeADT.");
//        }
//    }
//
//}
//
package uke10;
public class Main{
    public static double match(Person a, Person b) {
        MengdeADT<String> hobbyerA = a.getHobbyer();
        MengdeADT<String> hobbyerB = b.getHobbyer();

        MengdeADT<String> fellesHobbyer = hobbyerA.snitt(hobbyerB);

        MengdeADT<String> kunHosA = hobbyerA.minus(fellesHobbyer);
        MengdeADT<String> kunHosB = hobbyerB.minus(fellesHobbyer);

        int antallFelles = fellesHobbyer.antallElementer();
        int antallKunHosDenEne = kunHosA.antallElementer();
        int antallKunHosDenAndre = kunHosB.antallElementer();
        int antallTotalt = hobbyerA.antallElementer() + hobbyerB.antallElementer() - antallFelles;

        if (antallTotalt == 0) {
            return 1.0; 
        }

        return (double)(antallFelles - (antallKunHosDenEne + antallKunHosDenAndre)) / antallTotalt;
    }

    public static void main(String[] args) {
        Person arne = new Person("Arne", "jakt", "sykling", "venner", "data");
        Person bjorn = new Person("Bjorn", "jakt", "sykling", "matlaging", "venner");
        Person charlotte = new Person("Charlotte", "yoga", "svømming", "data", "venner");

        double matchAB = match(arne, bjorn);
        double matchAC = match(arne, charlotte);
        double matchBC = match(bjorn, charlotte);

        System.out.println("Match score between " + arne.getNavn() + " and " + bjorn.getNavn() + ": " + matchAB);
        System.out.println("Match score between " + arne.getNavn() + " and " + charlotte.getNavn() + ": " + matchAC);
        System.out.println("Match score between " + bjorn.getNavn() + " and " + charlotte.getNavn() + ": " + matchBC);

        if (matchAB > matchAC && matchAB > matchBC) {
            System.out.println("Best match: " + arne.getNavn() + " and " + bjorn.getNavn());
        } else if (matchAC > matchAB && matchAC > matchBC) {
            System.out.println("Best match: " + arne.getNavn() + " and " + charlotte.getNavn());
        } else if (matchBC > matchAB && matchBC > matchAC) {
            System.out.println("Best match: " + bjorn.getNavn() + " and " + charlotte.getNavn());
        } else {
            System.out.println("There is a tie in the best match.");
        }

        System.out.println("Match score for " + arne.getNavn() + " with themselves: " + match(arne, arne));
    }
}
