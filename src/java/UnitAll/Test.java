package UnitAll;

abstract public class Test {
    protected String m_nom;

    public Test() {
    }

    public Test(String nom) {
        m_nom = nom;
    }

    abstract public int calculPourcentageReussite();

    abstract public void afficheResultats();
}
