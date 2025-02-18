import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class trabalhoFinal {
    public static void main(String[] args) throws FileNotFoundException {
        menuGeral(); //chama o menu principal(admin e cliente)
    }

    //o menu principal é direcionado ao menu admin ou cliente - é para escolher uma das opçoes de forma mais organizada
    public static void menuGeral() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int opcaoUser;

        do {
            System.out.println("=======================");
            System.out.println("      Game Start!      ");
            System.out.println("=======================");
            System.out.println("1. Administrador");
            System.out.println("2. Cliente");
            System.out.println("3. Sair");
            System.out.println("-.-.-.-.-.-.-.-.-.-.-.-");
            System.out.print("Escolha a opção: ");
            opcaoUser = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoUser) {
                case 1:
                    senhaAdm(); //aqui você vai chama o menu do admin
                    break;
                case 2:
                    menuCliente(); //aqui você vai chama o menu do cliente
                    break;
                case 3:
                    System.out.println("Saindo... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcaoUser != 3); //sai apenas quando a opção for 3
    }
    // a senha do admin antes de acessar o menu
    public static void senhaAdm() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira a senha do ADMIN: ");
        String senha = scanner.nextLine();

        if (senha.equals("123456789")) { //senha correta
            menuAdministrador(); //chama o menu do admin
        } else {
            System.out.println("Senha Incorreta. Retornando ao menu principal...");
        }
    }

    //menu só para o admin
    public static void menuAdministrador() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("=======================");
            System.out.println("       Menu ADMIN      ");
            System.out.println("=======================");
            System.out.println("1. Exibir o conteúdo do arquivo na tela.");
            System.out.println("2. Mostrar número total de vendas e valor total acumulado.");
            System.out.println("3. Calcular lucro total - 20% do valor das vendas.");
            System.out.println("4. Mostrar informações de um cliente pelo ID");
            System.out.println("5. Mostrar jogo mais caro vendido e seus compradores.");
            System.out.println("6. Exibir o melhor cliente.");
            System.out.println("0. Voltar ao menu principal");
            System.out.println("-.-.-.-.-.-.-.-.-.-");
            System.out.println("Escolha uma opção: ");
            System.out.println("-.-.-.-.-.-.-.-.-.-");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    exibirArquivo();
                    break;
                case 2:
                    mostrarTotais();
                    break;
                case 3:
                    calcularLucro();
                    break;
                case 4: {
                    System.out.print("Escolha um ID do Cliente: ");
                    int idCliente = scanner.nextInt();
                    scanner.nextLine();
                    exibirClientePorId(idCliente);
                    break;
                }
                case 5:
                    mostrarJogoMaisCaro();
                    break;
                case 6:
                    exibirMelhorCliente();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA!!!");
            }
        } while (option != 0); //se a opçao for 0 volta ao menu principal
    }
    //menu só para o cliente
    public static void menuCliente() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("=======================");
            System.out.println("      Menu CLIENTE     ");
            System.out.println("=======================");
            System.out.println("1. Registrar um novo cliente.");
            System.out.println("2. Verificar vagas de estacionamento disponíveis.");
            System.out.println("3. Exibir todos os jogos disponíveis.");
            System.out.println(" NÃO CONSEGUI FAZER A EDITORA :( ");
            System.out.println("0. Voltar ao menu principal");
            System.out.println("-.-.-.-.-.-.-.-.-.-");
            System.out.println("Escolha uma opção: ");
            System.out.println("-.-.-.-.-.-.-.-.-.-");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    verificarVagasEstacionamento();
                    break;
                case 3:
                    exibirJogosDisponiveis();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA!!!");
            }
        } while (option != 0); //se a opçao for 0 volta ao menu principal
    }
    //aqui é a parte de exibir o conteúdo do ficheiro CSV:
    public static void exibirArquivo() throws FileNotFoundException {
        //caminho do arquivo:
        File file = new File("Algoritmia/ProjetoFinal/Ficheiro/GameStart_V2.csv");
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            System.out.println(fileScanner.nextLine());//mostrar o conteúdo linha a linha
        }
        fileScanner.close(); //fechar o scanner depois da leitura
    }

    //mostrar número total de vendas e valor total:
    public static void mostrarTotais() throws FileNotFoundException {
        File file = new File("Algoritmia/ProjetoFinal/Ficheiro/GameStart_V2.csv");
        Scanner fileScanner = new Scanner(file);
        fileScanner.nextLine(); //ignorar o cabeçalho

        int totalVendas = 0;
        double valorTotal = 0;

        while (fileScanner.hasNextLine()) {
            String[] linha = fileScanner.nextLine().split(";");// divide por ponto e virgula como mostra no ficheiro csv
            valorTotal += Double.parseDouble(linha[8]); //coluna do valor da venda
            totalVendas++;
        }

        System.out.println("Total de Vendas: " + totalVendas);
        System.out.println("Valor Total Acumulado: Euro " + valorTotal);

        fileScanner.close();
    }

    //calcular o lucro dos 20% do total de vendas
    public static void calcularLucro() throws FileNotFoundException {
        File file = new File("Algoritmia/ProjetoFinal/Ficheiro/GameStart_V2.csv");
        Scanner fileScanner = new Scanner(file);
        fileScanner.nextLine();

        double valorTotal = 0;

        while (fileScanner.hasNextLine()) {
            String[] linha = fileScanner.nextLine().split(";");
            valorTotal += Double.parseDouble(linha[8]);
        }

        double lucro = valorTotal * 0.2; //calcula 20% do valor total
        System.out.println("Lucro total: Euro " + lucro);

        fileScanner.close();
    }

    //exibir informaçoes de um cliente baseado pelo ID:
    public static void exibirClientePorId(int idCliente) throws FileNotFoundException {
        File file = new File("Algoritmia/ProjetoFinal/Ficheiro/GameStart_V2.csv");
        Scanner fileScanner = new Scanner(file);
        fileScanner.nextLine();

        boolean clienteEncontrado = false; //o boolean vai ajudar a olhar se o cliente foi encontrado

        while (fileScanner.hasNextLine()) {
            String[] linha = fileScanner.nextLine().split(";");
            if (Integer.parseInt(linha[1]) == idCliente) { //aqui vai verificar coluna do ID do cliente
                System.out.println("Informações do Cliente:");
                System.out.println("Nome: " + linha[2]);
                System.out.println("Contato: " + linha[3]);
                System.out.println("Email: " + linha[4]);
                clienteEncontrado = true; //aqui vai marcarmque o cliente foi encontrado
                break; //o break vai interromper a leitura do ficheiro
            }
        }

        if (!clienteEncontrado){
            System.out.println("Cliente com ID" + idCliente + "não encontrado");
        }
        fileScanner.close(); //fechando o scanner depois da leitura
    }

    //exibir o jogo mais caro e seus compradores:
    public static void mostrarJogoMaisCaro() throws FileNotFoundException {
        File file = new File("Algoritmia/ProjetoFinal/Ficheiro/GameStart_V2.csv");
        Scanner fileScanner = new Scanner(file);
        fileScanner.nextLine();

        double maiorPreco = 0;
        String jogoMaisCaro = "";
        String compradores = "";

        while (fileScanner.hasNextLine()) {
            String[] linha = fileScanner.nextLine().split(";");
            double precoAtual = Double.parseDouble(linha[8]);

            if (precoAtual > maiorPreco) {
                maiorPreco = precoAtual;
                jogoMaisCaro = linha[7]; //nome do jogo
                compradores = linha[2]; //nome do comprador
            } else if (precoAtual == maiorPreco) {
                compradores += "," + linha[2]; // aqui adiciona mais compradores se o preço for o mesmo
            }
        }

        System.out.println("Jogo mais Caro: " + jogoMaisCaro + " (Euro " + maiorPreco + ")");
        System.out.println("Compradores: " + compradores);
        fileScanner.close();
    }

    //exibir o melhor cliente (aquele que gastou mais):
    public static void exibirMelhorCliente() throws FileNotFoundException {
        File file = new File("Algoritmia/ProjetoFinal/Ficheiro/GameStart_V2.csv");
        Scanner fileScanner = new Scanner(file);
        fileScanner.nextLine();

        //aqui vai inicializar as variaveis para armazenar os dados do melhor cliente
        int melhorClienteId = -1;
        double maiorGasto = 0;
        String melhorClienteNome = "";
        String melhorClienteContato = "";
        String melhorClienteEmail = "";

        while (fileScanner.hasNextLine()) {  //vai lendo linha por linha
            String[] linha = fileScanner.nextLine().split(";");
            int idCliente = Integer.parseInt(linha[1]);//vai ter o id do cliente convertido para inteiro
            double gasto = Double.parseDouble(linha[8]);//vai ter o gasto do cliente convertido para double

            //vai verificar se o gasto atual é maior que o maior gasto registrado
            if (gasto > maiorGasto) {
                maiorGasto = gasto;
                melhorClienteId = idCliente;
                melhorClienteNome = linha[2];
                melhorClienteContato = linha[3];
                melhorClienteEmail = linha[4];
            }
        }
        //e depois vai verificar se encontrar algum cliente com maior gasto
        if (melhorClienteId != -1) {
            //os dados do melhor cliente
            System.out.println("Melhor Cliente: " + melhorClienteNome);
            System.out.println("Contato: " + melhorClienteContato);
            System.out.println("Email: " + melhorClienteEmail);
            System.out.println("Total Gasto: Euro " + maiorGasto);
        } else {
            //e se caso não for encontrado nenhum cliente, vai mostrar essa mensagem
            System.out.println("NENHUM CLIENTE ENCONTRADO.");
        }
        fileScanner.close();
    }

    //registrar um novo cliente:
    public static void registrarCliente() {
        Scanner scanner = new Scanner(System.in);

        //aqui vai pedir pro usuario inserir o nome, contato e email do cliente
        System.out.println("Insira o Nome do cliente: ");
        String nome = scanner.nextLine();//e aqui armazena o nome do cliente
        System.out.println("Insira o Contato do cliente: ");
        String contato = scanner.nextLine();//e aqui armazena o contato do cliente
        System.out.println("Insira o Email do cliente: ");
        String email = scanner.nextLine();//e aqui armazena o email do cliente

        //aqui mostra a mensagem de sucesso pois o registro dos dados nome,contato e mail
        System.out.println("_._._._._._._._._._._._._._._._._");
        System.out.println("Cliente Registrado com Sucesso! ");
        System.out.println("_._._._._._._._._._._._._._._._._");
        System.out.println("Nome: " + nome);
        System.out.println("Contato: " + contato);
        System.out.println("Email: " + email);
    }

    //verificar vagas de estacionamento:
    public static void verificarVagasEstacionamento() {
        System.out.println("Vagas Disponíveis: ");

        //calcula e mostra as vagas disponíveis:
        for (int n = 1; n * (n + 1) / 2 <= 121; n++) {
            int vaga = n * (n + 1) / 2;//para calcular a vaga

            //mostra a vaga apenas se o numero da vaga for divisivel por 5
            if (vaga % 5 == 0) {
                System.out.println(vaga); // e aqui vai mostrar as vagas disponiveis.
            }
        }
    }

    //exibir os jogos disponíveis:
    public static void exibirJogosDisponiveis() throws FileNotFoundException {
        File file = new File("Algoritmia/ProjetoFinal/Ficheiro/GameStart_V2.csv");
        Scanner fileScanner = new Scanner(file);
        fileScanner.nextLine();

        //vai lendo linha por linha e mostra os nomes dos jogos
        while (fileScanner.hasNextLine()) {
            String[] linha = fileScanner.nextLine().split(";");
            String jogo = linha[7]; //nome do jogo da coluna 7

            System.out.println(jogo); //vai mostrar o jogo
        }
        fileScanner.close();
    }



}
