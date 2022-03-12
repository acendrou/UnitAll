package UnitAll;

import java.util.Vector;

public class TestComplique<parametre, resultat> extends Test {
    private Vector<Boolean> m_reussites;
    private Vector<String> m_noms;

    private Vector<Vector<parametre>> m_entrees;
    private Vector<String> m_nomEntrees;
    private Vector<Vector<resultat>> m_sorties;
    private Vector<String> m_nomSorties;

    public TestComplique() {
    }


    public TestComplique(String nom) {
        super(nom);
        m_reussites = new Vector<Boolean>();
        m_noms = new Vector<String>();
        m_entrees = new Vector<Vector<parametre>>();
        m_nomEntrees = new Vector<String>();
        m_sorties = new Vector<Vector<resultat>>();
        m_nomSorties = new Vector<String>();
    }


    public void ajoutTest(String nom, boolean reussite, String nomEntree, Vector<parametre> entree, String nomSortie, Vector<resultat> sortie) {
        m_reussites.add(reussite);
        m_noms.add(nom);

        m_entrees.add(entree);
        m_nomEntrees.add(nomEntree);
        m_sorties.add(sortie);
        m_nomSorties.add(nomSortie);
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

            if (!m_reussites.elementAt(i)) {
                System.out.print("      ENTREE(S) : " + m_nomEntrees.elementAt(i) + " : ");

                m_entrees.elementAt(i).stream().map(entree -> " ~ " + entree).forEach(System.out::print);

                System.out.println("");
                System.out.print("      SORTIE(S) : " + m_nomSorties.elementAt(i) + " : ");

                m_sorties.elementAt(i).forEach(System.out::print);
            }
            System.out.println("");
        }

        System.out.println("");
        System.out.println("Reussite : " + calculPourcentageReussite() + " %");
    }

}
