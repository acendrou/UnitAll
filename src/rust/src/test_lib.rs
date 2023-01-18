use std::fmt::Display;

pub(crate) trait Test {
    fn calcul_pourcentage_reussite(&self) -> f64;
    fn affiche_resultats(&self) -> ();
}

pub(crate) struct TestSimple {
    pub(crate) nom: String,
    pub(crate) reussites: Vec<bool>,
    pub(crate) noms: Vec<String>,
}

impl Test for TestSimple {
    fn calcul_pourcentage_reussite(&self) -> f64 {
        let mut nb_reussite: i64 = 0;

        for reussite in self.reussites.iter() {
            if *reussite {
                nb_reussite += 1;
            }
        }
        return ((nb_reussite as f64) / (self.reussites.len() as f64)) * 100.0;
    }

    fn affiche_resultats(&self) -> () {
        println!("Nom du Test : {}", self.nom);
        println!();

        for i in 0..=self.reussites.len() - 1 {
            let message = if self.reussites[i] { " Pass" } else { " Fail" };
            println!("{} | {}", message, self.noms[i]);
        }

        println!();
        println!("Reussite : {} %", self.calcul_pourcentage_reussite());
    }
}

impl TestSimple {
    pub(crate) fn ajout_test(&mut self, nom: String, reussite: bool) -> () {
        self.reussites.push(reussite);
        self.noms.push(nom);
    }
}

pub(crate) struct TestComplique<Parametre, Resultat> {
    pub(crate) nom: String,
    pub(crate) reussites: Vec<bool>,
    pub(crate) noms: Vec<String>,

    pub(crate) entrees: Vec<Vec<Parametre>>,
    pub(crate) nom_entrees: Vec<String>,
    pub(crate) sorties: Vec<Vec<Resultat>>,
    pub(crate) nom_sorties: Vec<String>,
}

impl<Parametre: Display, Resultat: Display> Test for TestComplique<Parametre, Resultat> {
    fn calcul_pourcentage_reussite(&self) -> f64 {
        let mut nb_reussite: i64 = 0;

        for reussite in self.reussites.iter() {
            if *reussite {
                nb_reussite += 1;
            }
        }
        return ((nb_reussite as f64) / (self.reussites.len() as f64)) * 100.0;
    }

    fn affiche_resultats(&self) -> () {
        println!("Nom du Test : {}", self.nom);
        println!();

        for i in 0..=self.reussites.len() - 1 {
            let message = if self.reussites[i] { " Pass" } else { " Fail" };
            println!("{} | {}", message, self.noms[i]);

            if !self.reussites[i] {
                print!("      ENTREE(S) : {} : ", self.nom_entrees[i]);

                for entree in self.entrees[i].iter() {
                    print!(" ~ {}", entree);
                }
                println!();

                print!("      SORTIE(S) : {} : ", self.nom_sorties[i]);
                for sortie in self.sorties[i].iter() {
                    print!("{}", sortie);
                }
            }
            println!();
        }

        println!();
        println!("Reussite : {} %", self.calcul_pourcentage_reussite());
    }
}

impl<Parametre, Resultat> TestComplique<Parametre, Resultat> {
    pub(crate) fn ajout_test(
        &mut self,
        nom: String,
        reussite: bool,
        nom_entree: String,
        entree: Vec<Parametre>,
        nom_sortie: String,
        sortie: Vec<Resultat>,
    ) -> () {
        self.reussites.push(reussite);
        self.noms.push(nom);

        self.entrees.push(entree);
        self.nom_entrees.push(nom_entree);
        self.sorties.push(sortie);
        self.nom_sorties.push(nom_sortie);
    }
}

pub(crate) struct LotTest {
    pub(crate) nom: String,
    pub(crate) tests: Vec<Box<dyn Test>>,
}

impl LotTest {
    pub(crate) fn ajout_lot_test(&mut self, test: Box<dyn Test>) -> () {
        self.tests.push(test);
    }

    pub(crate) fn calcul_reussite(&self) -> bool {
        let mut reussite: bool = true;

        for test in self.tests.iter() {
            if test.calcul_pourcentage_reussite() < 100.0 {
                reussite = false;
                return reussite;
            }
        }

        return reussite;
    }

    pub(crate) fn affiche_resultats(&self) -> () {
        println!("Nom du Lot de Test : {}", self.nom);
        println!("********************");

        for test in self.tests.iter() {
            println!();
            test.affiche_resultats();
            println!();
        }
    }
}

impl Drop for LotTest {
    fn drop(&mut self) {}
}
