package com.project.SpringBank.Utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FonctionsGeneratives {

    private FonctionsGeneratives(){

    }

    public static String calculerCleRib(String codeBanque, String codeGuichet, Long numeroCompte){
            long calcul = (89 * Long.parseLong(codeBanque)
            + 15 * Long.parseLong(codeGuichet)
            + 3 * numeroCompte) % 97;

        return String.format("%02d", 97 - calcul);
    }

    public static String genererIban(Long numeroCompte) {
        String cleRib = calculerCleRib(BanqueConstantes.CODE_AGENCE, BanqueConstantes.CODE_GUICHET, numeroCompte);

        return formaterIban(numeroCompte, cleRib);
    }

    private static String formaterIban(Long numeroCompte, String cleRib) {
        return BanqueConstantes.CODE_PAYS + BanqueConstantes.CLE_CONTROLE_IBAN + BanqueConstantes.CODE_AGENCE +
                BanqueConstantes.CODE_GUICHET + numeroCompte + cleRib;
    }

    public static long genererNumeroCompte() {
        long min = 100_000_000_00L;
        long max = 999_999_999_99L;
        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }

    public static int genererCodeSecurite(){
        SecureRandom random = new SecureRandom();
        return 100 + random.nextInt(900);
    }


}
