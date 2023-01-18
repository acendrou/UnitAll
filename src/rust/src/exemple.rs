fn somme(premier_terme: i64, deuxieme_terme: i64, troisieme_terme: i64) -> i64 {
    let res: i64 = premier_terme + deuxieme_terme + troisieme_terme;
    return res;
}

fn moyenne(donnees: &Vec<f64>) -> f64 {
    let mut moy: f64 = 0.0;

    for elem in donnees.iter() {
        moy += elem;
    }

    moy = moy / donnees.len() as f64;
    return moy;
}

fn fait_test1() -> crate::test_lib::TestSimple {
    let mut sommes = crate::test_lib::TestSimple {
        nom: String::from("Somme"),
        reussites: Vec::new(),
        noms: Vec::new(),
    };

    let mut reussite: bool = false;

    // test 1
    let mut resultat: i64 = somme(1, 2, 3);
    if resultat == 6 {
        reussite = true;
    }

    sommes.ajout_test(String::from("Somme 1"), reussite);

    // test 2
    resultat = somme(1, 1, 1);
    reussite = false;
    if resultat == 3 {
        reussite = true;
    }

    sommes.ajout_test(String::from("Somme 2"), reussite);

    return sommes;
}

fn test1() -> bool {
    let mut test1 = crate::test_lib::LotTest {
        nom: String::from("Calculs de sommes"),
        tests: Vec::new(),
    };

    let sommes = fait_test1();

    test1.ajout_lot_test(Box::new(sommes));

    test1.affiche_resultats();

    return test1.calcul_reussite();
}

fn fait_test2() -> crate::test_lib::TestComplique<f64, f64> {
    let mut moyennes = crate::test_lib::TestComplique {
        nom: String::from("Moyenne"),
        reussites: Vec::new(),
        noms: Vec::new(),
        entrees: Vec::new(),
        nom_entrees: Vec::new(),
        sorties: Vec::new(),
        nom_sorties: Vec::new(),
    };

    let mut reussite: bool = false;

    let mut termes: Vec<f64>;

    // test 1
    termes = vec![1.0, 2.0, 3.0, 4.0];
    let mut resultat: f64 = moyenne(&termes);
    let mut resultat_vrai: f64 = 2.5;
    if resultat_vrai == resultat {
        reussite = true;
    }

    moyennes.ajout_test(
        String::from("Moyenne 1"),
        reussite,
        String::from("termes"),
        termes,
        String::from("moyenne"),
        vec![resultat],
    );

    // test 2
    termes = vec![23.0, 30.0, 21.0, 14.0, 9.0, 10.0, 15.0];
    resultat = moyenne(&termes);
    resultat_vrai = 17.428571428571427;
    reussite = false;
    if resultat_vrai == resultat {
        reussite = true;
    }

    moyennes.ajout_test(
        String::from("Moyenne 2"),
        reussite,
        String::from("termes"),
        termes,
        String::from("moyenne"),
        vec![resultat],
    );

    // test 3
    termes = vec![36.0, 35.0, 30.0, 18.0, 17.0, 7.0, 2.0, 1.0, 0.0];
    resultat = moyenne(&termes);
    resultat_vrai = 16.22222222222222;
    reussite = false;
    if resultat_vrai == resultat {
        reussite = true;
    }

    moyennes.ajout_test(
        String::from("Moyenne 3"),
        reussite,
        String::from("termes"),
        termes,
        String::from("moyenne"),
        vec![resultat],
    );

    return moyennes;
}

fn test2() -> bool {
    let mut test2 = crate::test_lib::LotTest {
        nom: String::from("Calculs de moyennes"),
        tests: Vec::new(),
    };

    let moyennes = fait_test2();
    test2.ajout_lot_test(Box::new(moyennes));

    test2.affiche_resultats();

    return test2.calcul_reussite();
}

pub(crate) fn exemple_main() {
    let mut reussite: bool = true;

    if !test1() {
        reussite = false;
    }

    if !test2() {
        reussite = false;
    }

    if !reussite {
        std::process::exit(1);
    }
}
