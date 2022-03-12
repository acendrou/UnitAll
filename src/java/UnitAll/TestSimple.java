package UnitAll;

import java.util.Vector;

public class TestSimple extends Test {
    private Vector<Boolean> m_reussites;
    private Vector<String> m_noms;

    public TestSimple() {
    }


    public TestSimple(String nom) {
        super(nom);
        m_reussites = new Vector<Boolean>();
        m_noms = new Vector<String>();
    }


    public void ajoutTest(String nom, boolean reussite) {
        m_reussites.add(reussite);
        m_noms.add(nom);
    }

    public int calculPourcentageReussite() {
        int nbReussite = 0;

        for (boolean reussite : m_reussites) {
            if (reussite) {
                nbReussite++;
            }
        }
        return (int) ((float) (nbReussite) / (float) (m_reussites.size()) * 100);
    }

    public void afficheResultats() {
        System.out.println("Nom du Test : " + m_nom);
        System.out.println("");


        for (int i = 0; i < m_reussites.size(); i++) {
            System.out.println((m_reussites.elementAt(i) ? " Pass" : " Fail") + " | " + m_noms.elementAt(i));
        }

        System.out.println("");
        System.out.println("Reussite : " + calculPourcentageReussite() + " %");

    }
}

