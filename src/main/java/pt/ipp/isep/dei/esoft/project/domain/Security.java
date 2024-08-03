package pt.ipp.isep.dei.esoft.project.domain;


import java.io.Serializable;
import java.util.Random;

/**
 * The type Security.
 */
public class Security implements Serializable {
    /**
     * gera passowrd de 8 caracteres com pelo menos 3 maiusculas e 2 digitos
     *
     * @return password string
     */
    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder();//StringBuilder permite a criação de strings mutáveis
        Random random = new Random();//Random permite a geração de números aleatórios
        int uppercaseCount = 0;
        int numberCount = 0;
        int uCount = 0;
        int nCount = 0;
        boolean flag=false;


            while (password.length() < 8) {
                int r = random.nextInt(3);
                if (r == 0 && uppercaseCount < 3) {
                    password.append((char) (random.nextInt(26) + 'A'));
                    uppercaseCount++;
                } else if (r == 1 && numberCount < 2) {
                    password.append(random.nextInt(10));
                    numberCount++;
                } else {
                    int r2 = random.nextInt(3);
                    switch (r2) {
                        case 0:
                            password.append((char) (random.nextInt(26) + 'A')); // letra maiúscula
                            break;
                        case 1:
                            password.append((char) (random.nextInt(26) + 'a')); // letra minúscula
                            break;
                        case 2:
                            password.append((char) (random.nextInt(10) + '0')); // número
                            break;
                    }
                }
            }
            if (uppercaseCount < 3) {
                for (int i = 0; i < 3 - uppercaseCount; i++) {
                    for (int j = 0; j < password.length(); j++) {
                        char c = password.charAt(j);
                        if (Character.isLowerCase(c)) {
                            password.setCharAt(j, (char) (random.nextInt(26) + 'A'));
                        }
                    }
                }
            }


            if (numberCount < 2) {
                for (int i = 0; i < 2 - numberCount; i++) {
                    for (int j = 0; j < password.length(); j++) {
                        char c = password.charAt(j);
                        if (Character.isDigit(c)) {
                            password.setCharAt(j, (char) (random.nextInt(10) + '0'));
                        }
                    }
                }
            }


        return password.toString();
    }
}


