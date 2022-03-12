import UnitAll.LotTest;
import UnitAll.Test;
import UnitAll.TestComplique;
import UnitAll.TestSimple;

import java.util.Collections;
import java.util.Vector;

public class Exemple {
    public void marche() {
        boolean reussite = true;

        if (!test1()) {
            reussite = false;
        }

        if (!test2()) {
            reussite = false;
        }

        if (!reussite) {
            System.exit(1);
        }
        System.exit(0);
    }

    private float somme(float premierTerme, float deuxiemeTerme, float troisiemeTerme) {
        float res = 0;

        res = premierTerme + deuxiemeTerme + troisiemeTerme;

        return res;
    }

    private float moyenne(Vector<Integer> donnees) {
        float moy = 0;

        for (Integer elem : donnees) {
            moy += elem;
        }

        moy = moy / donnees.size();

        return moy;
    }

    private void FaitTest1(LotTest test) {
        Test sommes = new TestSimple("Somme");
        test.ajoutLotTest(sommes);

        boolean reussite = false;
        int resultat = 0;


        // test 1
        resultat = (int) somme(1, 2, 3);
        reussite = false;
        if (resultat == 6) {
            reussite = true;
        }

        ((TestSimple) sommes).ajoutTest("Somme 1", reussite);


        // test 2
        resultat = (int) somme(1, 1, 1);
        reussite = false;
        if (resultat == 3) {
            reussite = true;
        }

        ((TestSimple) sommes).ajoutTest("Somme 2", reussite);
    }

    private boolean test1() {
        LotTest test1;
        test1 = new LotTest("Calculs de sommes");

        FaitTest1(test1);

        test1.afficheResultats();

        return test1.calculReussite();
    }

    private void FaitTest2(LotTest test) {

        Test moyennes;
        moyennes = new TestComplique<Integer, Float>("Moyenne");
        test.ajoutLotTest(moyennes);

        boolean reussite = false;


        float resultatVrai;
        float resultat;

        Vector<Integer> termes;
        termes = new Vector<Integer>();

        Vector<Float> valeurResultat;
        valeurResultat = new Vector<Float>();

        // test 1
        Integer[] termesTemp = {1, 2, 3, 4};
        Collections.addAll(termes, termesTemp);
        resultat = moyenne(termes);
        resultatVrai = 2.5F;
        reussite = false;
        if (resultatVrai == resultat) {
            reussite = true;
        }

        valeurResultat.removeAllElements();
        valeurResultat.add(resultat);
        ((TestComplique<Integer, Float>) moyennes).ajoutTest("Moyenne 1", reussite, "termes", termes, "moyenne", valeurResultat);


        // test 2
        termesTemp = new Integer[]{23, 30, 21, 14, 9, 10, 15};
        termes.removeAllElements();
        Collections.addAll(termes, termesTemp);
        resultat = moyenne(termes);
        resultatVrai = 17.4285717f;
        reussite = false;
        if (resultatVrai == resultat) {
            reussite = true;
        }

        valeurResultat.removeAllElements();
        valeurResultat.add(resultat);
        ((TestComplique<Integer, Float>) moyennes).ajoutTest("Moyenne 2", reussite, "termes", termes, "moyenne", valeurResultat);


        // test 3
        termesTemp = new Integer[]{36, 35, 30, 18, 17, 7, 2, 1, 0};
        termes.removeAllElements();
        Collections.addAll(termes, termesTemp);
        resultat = moyenne(termes);
        resultatVrai = 16.22222137f;
        reussite = false;
        if (resultatVrai == resultat) {
            reussite = true;
        }


        valeurResultat.removeAllElements();
        valeurResultat.add(resultat);
        ((TestComplique<Integer, Float>) moyennes).ajoutTest("Moyenne 3", reussite, "termes", termes, "moyenne", valeurResultat);
    }

    private boolean test2() {
        LotTest test2;
        test2 = new LotTest("Calculs de moyennes");

        FaitTest2(test2);

        test2.afficheResultats();

        return test2.calculReussite();
    }
}
