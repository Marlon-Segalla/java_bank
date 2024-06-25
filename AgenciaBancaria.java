package Programa;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AgenciaBancaria {

    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();
        login();
    }

    public static void login() {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog("--- Tela de Login ---\n" +
                "Opção 1 - Login\n" + "Opção 2 - Criar Conta\n" + "Opção 3 - Sair"));

        switch (opcao) {
            case 1:
                realizarLogin();
                break;
            case 2:
                criarConta();
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Obrigado por usar nossa Agência!");
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                login();
                break;
        }
    }

    public static void realizarLogin() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Número da conta:"));
        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
            operacoes();
        } else {
            JOptionPane.showMessageDialog(null, "Conta não encontrada. Por favor, crie uma conta.");
            criarConta();
        }
    }

    public static void operacoes() {
        int operacao = Integer.parseInt(JOptionPane.showInputDialog("--- Selecione uma operação ---\n" +
                "Opção 1 - Criar conta\n" + "Opção 2 - Depositar\n" + "Opção 3 - Sacar\n" + "Opção 4 - Transferir\n" +
                "Opção 5 - Listar\n" + "Opção 6 - Sair"));

        switch (operacao) {
            case 1:
                criarConta();
                break;
            case 2:
                depositar();
                break;
            case 3:
                sacar();
                break;
            case 4:
                transferir();
                break;
            case 5:
                listarContas();
                break;
            case 6:
                JOptionPane.showMessageDialog(null, "Obrigado por usar nossa Agência!");
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                operacoes();
                break;
        }
    }

    public static void criarConta() {
        Cliente cliente = new Cliente();
        cliente.setNome(JOptionPane.showInputDialog("Nome:"));
        cliente.setCpf(JOptionPane.showInputDialog("CPF:"));
        cliente.setEmail(JOptionPane.showInputDialog("EMAIL:"));

        Conta conta = new Conta(cliente);
        contasBancarias.add(conta);
        JOptionPane.showMessageDialog(null, "Sua conta foi criada com sucesso!");
        operacoes();
    }

    private static Conta encontrarConta(int numeroConta) {
        Conta conta = null;
        if (contasBancarias.size() > 0) {
            for (Conta c : contasBancarias) {
                if (c.getNumeroConta() == numeroConta) {
                    conta = c;
                }
            }
        }
        return conta;
    }

    public static void depositar() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Número da conta para depósito:"));
        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            Double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Qual valor deseja depositar:"));
            conta.depositar(valorDeposito);
            JOptionPane.showMessageDialog(null, "Valor depositado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Conta não foi encontrada!");
        }
        operacoes();
    }

    public static void sacar() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Número da conta para saque:"));
        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            Double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Qual valor deseja sacar:"));
            conta.sacar(valorSaque);
        } else {
            JOptionPane.showMessageDialog(null, "Conta não foi encontrada!");
        }
        operacoes();
    }

    public static void transferir() {
        int numeroContaRemetente = Integer.parseInt(JOptionPane.showInputDialog("Número da conta do remetente:"));
        Conta contaRemetente = encontrarConta(numeroContaRemetente);

        if (contaRemetente != null) {
            int numeroContaDestinatario = Integer.parseInt(JOptionPane.showInputDialog("Número da conta do destinatário:"));
            Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

            if (contaDestinatario != null) {
                Double valor = Double.parseDouble(JOptionPane.showInputDialog("Qual valor deseja transferir:"));
                contaRemetente.transferir(contaDestinatario, valor);
                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Conta do destinatário não foi encontrada!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Conta do remetente não foi encontrada!");
        }
        operacoes();
    }

    public static void listarContas() {
        if (contasBancarias.size() > 0) {
            for (Conta conta : contasBancarias) {
                JOptionPane.showMessageDialog(null, conta);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não há contas cadastradas!");
        }
        operacoes();
    }
}
