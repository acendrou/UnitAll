#ifndef TESTLIB_H_INCLUDED
#define TESTLIB_H_INCLUDED

#include <iostream>
#include <vector>

class Test
{
protected:
    std::string m_nom;

public:
    Test() {};
    Test(std::string nom):m_nom(nom) {};
    virtual ~Test() {};

    virtual int calculPourcentageReussite() = 0;
    virtual void afficheResultats() = 0;
};

class TestSimple : public Test //classe fille
{
private:
    std::vector<bool> m_reussites;
    std::vector<std::string> m_noms;

public:
    TestSimple() {};
    TestSimple(std::string nom):Test(nom) {};
    ~TestSimple() {};

    void ajoutTest(std::string nom, bool reussite)
    {
        m_reussites.push_back(reussite);
        m_noms.push_back(nom);
    }

    int calculPourcentageReussite()
    {
        int nbReussite = 0;

        for (auto reussite : m_reussites)
        {
            if(reussite)
            {
                nbReussite++;
            }
        }
        return ((static_cast<float>(nbReussite) / static_cast<float>(m_reussites.size())) * 100);
    }

    void afficheResultats()
    {
        std::cout << "Nom du Test : " << m_nom << std::endl;
        std::cout << std::endl;

        for (unsigned int i = 0; i<m_reussites.size(); i++)
        {
            std::cout << (m_reussites[i] ? " Pass" : " Fail") << " | " << m_noms[i] << std::endl;
        }

        std::cout << std::endl;
        std::cout << "Reussite : " << calculPourcentageReussite() << " %" << std::endl;
    }
};

template<class parametre, class resultat>
class TestComplique : public Test
{
private:
    std::vector<bool> m_reussites;
    std::vector<std::string> m_noms;

    std::vector<std::vector<parametre>> m_entrees;
    std::vector<std::string> m_nomEntrees;
    std::vector<std::vector<resultat>> m_sorties;
    std::vector<std::string> m_nomSorties;

public:
    TestComplique() {};
    TestComplique(std::string nom):Test(nom) {};
    ~TestComplique() {};

    void ajoutTest(std::string nom, bool reussite, std::string nomEntree, std::vector<parametre> entree, std::string nomSortie, std::vector<resultat> sortie)
    {
        m_reussites.push_back(reussite);
        m_noms.push_back(nom);

        m_entrees.push_back(entree);
        m_nomEntrees.push_back(nomEntree);
        m_sorties.push_back(sortie);
        m_nomSorties.push_back(nomSortie);
    }

    int calculPourcentageReussite()
    {
        int nbReussite = 0;

        for (auto reussite : m_reussites)
        {
            if(reussite)
            {
                nbReussite++;
            }
        }
        return ((static_cast<float>(nbReussite) / static_cast<float>(m_reussites.size())) * 100);
    };

    void afficheResultats()
    {
        std::cout << "Nom du Test : " << m_nom << std::endl;
        std::cout << std::endl;

        for (unsigned int i = 0; i<m_reussites.size(); i++)
        {
            std::cout << (m_reussites[i] ? " Pass" : " Fail") << " | " << m_noms[i] << std::endl;

            if (!m_reussites[i])
            {
                std::cout << "      ENTREE(S) : " << m_nomEntrees[i] << " : ";
                for (auto entree : m_entrees[i])
                {
                    std::cout << " ~ " << entree ;
                }
                std::cout << std::endl;

                std::cout << "      SORTIE(S) : " << m_nomSorties[i] << " : ";
                for (auto sortie : m_sorties[i])
                {
                    std::cout << sortie;
                }
            }
            std::cout << std::endl;
        }

        std::cout << std::endl;
        std::cout << "Reussite : " << calculPourcentageReussite() << " %" << std::endl;
    };

};

class LotTest
{
private:
    std::string m_nom;
    std::vector<Test*> m_tests;
public:
    LotTest() {};
    LotTest(std::string nom): m_nom(nom) {};
    ~LotTest()
    {
        for(auto test : m_tests)
        {
            delete test;
        }
    };

    void ajoutLotTest(Test* test)
    {
        m_tests.push_back(test);
    }

    bool calculReussite()
    {
        bool reussite = true;

        for (auto test : m_tests)
        {
            if (test->calculPourcentageReussite() < 100)
            {
                reussite = false;
                return reussite;
            }
        }
        return reussite;
    }

    void afficheResultats()
    {
        std::cout << "Nom du Lot de Test : " << m_nom << std::endl;
        std::cout << "********************" << std::endl;

        for (auto test : m_tests)
        {
            std::cout << std::endl;
            test->afficheResultats();
            std::cout << std::endl;
        }
    }
};

#endif // TESTLIB_H_INCLUDED
