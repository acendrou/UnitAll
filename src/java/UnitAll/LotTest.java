package UnitAll;

import java.util.Vector;

public class LotTest {
    private String m_nom;
    private Vector<Test> m_tests;

    public LotTest() {
    }


    public LotTest(String nom) {
        m_nom = nom;
        m_tests = new Vector<Test>();
    }


    public void ajoutLotTest(Test test) {
        m_tests.add(test);
    }

    public boolean calculReussite() {
        boolean reussite = true;

        for (Test test : m_tests) {
            if (test.calculPourcentageReussite() < 100) {
                reussite = false;
                return reussite;
            }
        }
        return reussite;
    }

    public void afficheResultats() {
        System.out.println("Nom du Lot de Test : " + m_nom);
        System.out.println("********************");

        for (Test test : m_tests) {
            System.out.println("");
            test.afficheResultats();
            System.out.println("");
        }
    }
}
