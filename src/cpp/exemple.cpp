#include <vector>

#include "testLib.hpp"


float somme(float premierTerme, float deuxiemeTerme, float troisiemeTerme)
{
    float res = 0;

    res = premierTerme + deuxiemeTerme + troisiemeTerme;

    return res;
}


float moyenne(std::vector<int> donnees)
{
    float moy;

    for (auto elem : donnees)
    {
        moy += elem;
    }

    moy = moy / donnees.size();

    return moy;
}





void FaitTest1(LotTest& test)
{
    Test* sommes = new TestSimple("Somme");
    test.ajoutLotTest(sommes);

    bool reussite = false;
    int resultat = 0;


    // test 1
    resultat = somme(1, 2, 3);
    reussite = false;
    if (resultat == 6)
    {
        reussite = true;
    }

    dynamic_cast<TestSimple*>(sommes)->ajoutTest("Somme 1", reussite);


    // test 2
    resultat = somme(1, 1, 1);
    reussite = false;
    if (resultat== 3)
    {
        reussite = true;
    }

    dynamic_cast<TestSimple*>(sommes)->ajoutTest("Somme 2", reussite);
}


bool test1()
{
    LotTest test1("Calculs de sommes");

    FaitTest1(test1);

    test1.afficheResultats();

    return test1.calculReussite();
}

void FaitTest2(LotTest& test)
{
    std::cout.precision(10);

    Test* moyennes = new TestComplique<int,float>("Moyenne");
    test.ajoutLotTest(moyennes);

    bool reussite = false;


    float resultatVrai;
    float resultat;

    std::vector<int> termes;

    // test 1
    termes = {1, 2, 3, 4};
    resultat = moyenne(termes);
    resultatVrai = 2.5;
    reussite = false;
    if (resultatVrai == resultat)
    {
        reussite = true;
    }

    dynamic_cast<TestComplique<int,float>*>(moyennes)->ajoutTest("Moyenne 1", reussite, "termes", termes, "moyenne", {resultat});


    // test 2
    termes = {23, 30, 21, 14, 9, 10, 15};
    resultat = moyenne(termes);
    resultatVrai = 17.4285717f;
    reussite = false;
    if (resultatVrai == resultat)
    {
        reussite = true;
    }

    dynamic_cast<TestComplique<int,float>*>(moyennes)->ajoutTest("Moyenne 2", reussite, "termes", termes, "moyenne", {resultat});


    // test 3
    termes = {36, 35, 30, 18, 17, 7, 2, 1, 0};
    resultat = moyenne(termes);
    resultatVrai = 16.22222137f;
    reussite = false;
    if (resultatVrai == resultat)
    {
        reussite = true;
    }

    dynamic_cast<TestComplique<int,float>*>(moyennes)->ajoutTest("Moyenne 3", reussite, "termes", termes,"moyenne", {resultat});
}


bool test2()
{
    LotTest test2("Calculs de moyennes");

    FaitTest2(test2);

    test2.afficheResultats();

    return test2.calculReussite();
}


int main()
{
    bool reussite = true;

    if (!test1())
    {
        reussite = false;
    }

    if (!test2())
    {
        reussite = false;
    }

    if (!reussite)
    {
        return 1;
    }

    return 0;
}
