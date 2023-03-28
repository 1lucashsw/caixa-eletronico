import java.util.ArrayList;
import java.util.Scanner;


public class CaixaEletronico {

    public static void main(String[] args) {
        Scanner ler;
        String cpfcad = "123.456.789-00", cpf, senhacad = "01020304", senha, 
                contacad = "12345678-9", agenciacad = "1234", bancocad = "SANTANDER",
                conta, agencia, banco;
        int opcao, tamanho;
        double saldo = 1000, deposito, saque, transferencia;
        
        ArrayList<String> extrato = new ArrayList();

        Scanner sc = new Scanner(System.in);
        
        while(true){
            bemvindo();
        
            System.out.println();
            System.out.print("- Informe o seu CPF: ");
            cpf = sc.next();
            System.out.println();
        
            if(!cpf.equals(cpfcad)){
                System.out.println("-------- *CPF inválido!* --------");
                break;
            }else
                for(int i = 1; i <= 2; i++){
                    System.out.print("- Digite sua senha: ");
                    senha = sc.next(); 
                    if(senha.equals(senhacad)){
                            System.out.println("* Senha correta! *");
                            break;
                    }else if(!senha.equals(senhacad)){
                        System.out.println("* Senha incorreta! *");
                        System.out.println("Tentativas disponíveis: " + (2 - i));
                        System.out.println();
                            if(i == 2){
                                bloqueio();
                                System.out.println();
                                System.exit(0);
                            }
                    }
                }
                OUTER:  
                while (true) {
                    System.out.println();
                    menu();
                    System.out.print("Digite a opção desejada: ");
                    opcao = sc.nextInt();
                    System.out.println();
                    
                    switch (opcao) {
                        case 0:
                            break OUTER;
                        
                        case 1:
                            mostrarsaldo(saldo);
                        
                        case 2:
                            do{
                                System.out.print("Informe o valor do depósito: R$ ");
                                deposito = sc.nextDouble();
                                
                                if(deposito <= 0){
                                    System.out.println("* Valor inválido. Insira novamente! *");
                                    System.out.println();
                                }   
                            }while(deposito <= 0);
                            
                            saldo += deposito;
                            
                            System.out.println();
                            System.out.println("* Depósito efetuado com sucesso! *");
                            System.out.printf("Seu saldo agora é de: R$ %.2f.", saldo);
                            System.out.println();
                            extrato.add("Depositou: R$ " + Double.toString(deposito));
    
                        case 3:
                            do{
                                System.out.print("Informe o valor do saque: R$ ");
                                saque = sc.nextDouble();
                            
                                if(saque > saldo){
                                    System.out.println("* Você não possui saldo suficiente para efutar o saque! *");
                                    System.out.println();
                                }
                                if(saque <= 0){
                                    System.out.println("* Valor inválido. Insira novamente! *");
                                    System.out.println();
                                }
                            }while(saque > saldo || saque <= 0);
                            
                            saldo -= saque;
                            
                            System.out.println();
                            System.out.println("* Saque efetuado com sucesso! *");
                            System.out.printf("Seu saldo agora é de: R$ %.2f.", saldo);
                            System.out.println();
                            extrato.add("Sacou: R$ " + Double.toString(saque));                       

                        case 4:
                            do{
                                System.out.print("Informe o valor da transferência: R$ ");
                                transferencia = sc.nextDouble();
                            
                                if(transferencia <= 0){
                                    System.out.println("* Valor inválido. Insira novamente! *");
                                    System.out.println();
                                }else if(transferencia > saldo){
                                    System.out.println("* Você não possui saldo suficiente para efutar a transferência! *");
                                    System.out.println();
                                }
                            }while(transferencia <= 0 || transferencia > saldo);
                            
                            extrato.add("Transferiu: R$ " + Double.toString(transferencia));
                            
                            do{
                                System.out.print("Informe o número da conta: ");
                                conta = sc.next();
                                if(!conta.equals(contacad)){
                                    System.out.println("* Conta não encontrada! *");
                                    System.out.println();
                                }
                            }while(!conta.equals(contacad));
                            
                            do{
                                System.out.print("Informe o número da agência: ");
                                agencia = sc.next();
                                if(!agencia.equals(agenciacad)){
                                    System.out.println("* Agência não encontrada! *");
                                    System.out.println();
                                }
                            }while(!agencia.equals(agenciacad));
                            
                            do{
                                System.out.print("Informe o banco: ");
                                banco = sc.next().toUpperCase();
                                if(!banco.equals(bancocad)){
                                    System.out.println("* Conta não encontrada no respectivo banco! *");
                                    System.out.println();
                                }
                            }while(!banco.equals(bancocad));
                            
                            saldo -= transferencia;
                            
                            System.out.println();
                            System.out.println("* Tranferência efetuada com sucesso! *");
                            System.out.printf("Seu saldo agora é de: R$ %.2f.", saldo);
                            System.out.println();
                        
                        case 5:
                            tamanho = extrato.size();
                            System.out.println("* SEU EXTRATO: *");
                            for(int i = 0; i < tamanho; i++){
                                System.out.printf("%d°: %s", (i + 1), extrato.get(i));
                                System.out.println();
                            }        
                        
                        default:
                            System.out.println("* OPÇÃO INVÁLIDA! *");
                        }
                    }
                }  
            }
    public static void bemvindo(){
        System.out.println(" __________________________________ ");
        System.out.println("|                                  |");
        System.out.println("|         *SEJA BEM-VINDO!*        |");
        System.out.println("|__________________________________|");
    }
    public static void menu(){ 
        System.out.println(" __________________________________ ");
        System.out.println("|                                  |");
        System.out.println("|        OPÇÕES DISPONÍVEIS:       |");
        System.out.println("|            1 - SALDO;            |");
        System.out.println("|            2 - DEPÓSITO;         |");
        System.out.println("|            3 - SAQUE;            |");
        System.out.println("|            4 - TRANSFERÊNCIA;    |");
        System.out.println("|            5 - EXTRATO;          |");
        System.out.println("|            0 - SAIR;             |");
        System.out.println("|__________________________________|");
    }     
    public static void bloqueio(){
        System.out.println(" __________________________________ ");
        System.out.println("|                                  |");
        System.out.println("|       *MUITAS TENTATIVAS.*       |");
        System.out.println("|        *CONTA BLOQUEADA!*        |");
        System.out.println("|__________________________________|");
    }
    public static void mostrarsaldo(double saldo){
        System.out.println("------------------------------------");
        System.out.println("|         * SEU SALDO: *          |");
        System.out.printf("|           R$ %.2f            |" , saldo);
        System.out.println();
        System.out.println("------------------------------------");
    }
}