import java.util.ArrayList;
import java.util.Scanner;

public class SistemaMonitoramento {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EstacaoMonitoramento estacao = new EstacaoMonitoramento("EST-01", "Estação Córrego Central", "Zona Leste", "Ponte da Vila");
        SensorChuva sensorChuva = new SensorChuva();
        SensorNivelRio sensorRio = new SensorNivelRio();
        SensorUmidade sensorUmidade = new SensorUmidade();

        ArrayList<Double> histChuva = new ArrayList<>();
        ArrayList<Double> histRio = new ArrayList<>();
        ArrayList<Double> histUmidade = new ArrayList<>();

        DadosTerritorio territorio = new DadosTerritorio("Zona Leste", 18500, 50.0, 3.5, 85.0, "1234");
        EstrategiaPrevisao mediaMovel = new PrevisaoMediaMovel(3);
        EstrategiaPrevisao comparacao = new PrevisaoComparacao(0.5);

        // 3 leituras iniciais para começar o histórico
        for (int i = 0; i < 3; i++) {
            histChuva.add(sensorChuva.lerValor());
            histRio.add(sensorRio.lerValor());
            histUmidade.add(sensorUmidade.lerValor());
        }

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== MONITORAMENTO TERRITORIAL ===");
            System.out.println("1 - Verificar sensores");
            System.out.println("2 - Simular próxima leitura");
            System.out.println("3 - Rodar previsões e alertas");
            System.out.println("4 - Gerenciar dados do território");
            System.out.println("5 - Exibir status completo");
            System.out.println("6 - Simular 24 horas (Bônus)");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.println("\n" + estacao.verificarStatus());
                System.out.println("Chuva: " + histChuva.get(histChuva.size() - 1) + " mm | Operacional: " + sensorChuva.verificarFuncionamento());
                System.out.println("Rio: " + histRio.get(histRio.size() - 1) + " m | Operacional: " + sensorRio.verificarFuncionamento());
                System.out.println("Umidade: " + histUmidade.get(histUmidade.size() - 1) + "% | Operacional: " + sensorUmidade.verificarFuncionamento());

            } else if (opcao == 2) {
                histChuva.add(sensorChuva.lerValor());
                histRio.add(sensorRio.lerValor());
                histUmidade.add(sensorUmidade.lerValor());
                System.out.println("[OK] Novas leituras registradas! Total: " + histChuva.size());

            } else if (opcao == 3) {
                if (histChuva.size() < 5) {
                    System.out.println("\n[AVISO] Menos de 5 leituras (" + histChuva.size() + "). Previsão com baixa confiabilidade.");
                }
                double prevChuva = Math.max(mediaMovel.prever(histChuva), comparacao.prever(histChuva));
                double prevRio = Math.max(mediaMovel.prever(histRio), comparacao.prever(histRio));
                double prevUmid = Math.max(mediaMovel.prever(histUmidade), comparacao.prever(histUmidade));

                System.out.println("\n--- PREVISÕES ---");
                System.out.println("Chuva -> " + mediaMovel.gerarResumo(mediaMovel.prever(histChuva), "mm") + " | " + comparacao.gerarResumo(comparacao.prever(histChuva), "mm"));
                verificarAlerta("Sensor de Chuva", prevChuva, territorio.getLimiarChuva(), "mm");

                System.out.println("Rio -> " + mediaMovel.gerarResumo(mediaMovel.prever(histRio), "m") + " | " + comparacao.gerarResumo(comparacao.prever(histRio), "m"));
                verificarAlerta("Sensor de Nível do Rio", prevRio, territorio.getLimiarNivelRio(), "m");

                System.out.println("Umidade -> " + mediaMovel.gerarResumo(mediaMovel.prever(histUmidade), "%") + " | " + comparacao.gerarResumo(comparacao.prever(histUmidade), "%"));
                verificarAlerta("Sensor de Umidade", prevUmid, territorio.getLimiarUmidade(), "%");

            } else if (opcao == 4) {
                System.out.println("\nRegião: " + territorio.getNomeRegiao() + " | População: " + territorio.getPopulacao());
                System.out.println("Limiares -> Chuva: " + territorio.getLimiarChuva() + "mm | Rio: " + territorio.getLimiarNivelRio() + "m | Umidade: " + territorio.getLimiarUmidade() + "%");
                System.out.print("1-Alterar População | 2-Alterar Limiar Chuva: ");
                int sub = scanner.nextInt();
                scanner.nextLine();

                if (sub == 1) {
                    System.out.print("Nova população: ");
                    territorio.setPopulacao(scanner.nextInt());
                    scanner.nextLine();
                } else if (sub == 2) {
                    System.out.print("Código de acesso (1234): ");
                    String cod = scanner.nextLine();
                    System.out.print("Novo limiar de chuva (mm): ");
                    territorio.setLimiarChuva(scanner.nextDouble(), cod);
                    scanner.nextLine();
                }

            } else if (opcao == 5) {
                System.out.println("\n=== STATUS COMPLETO ===");
                System.out.println(estacao.verificarStatus());
                System.out.println("Território: " + territorio.getNomeRegiao() + " (" + territorio.getPopulacao() + " hab.)");
                System.out.println("Histórico Chuva: " + histChuva);
                System.out.println("Histórico Rio: " + histRio);
                System.out.println("Histórico Umidade: " + histUmidade);

            } else if (opcao == 6) {
                System.out.println("\n=== SIMULAÇÃO 24 HORAS ===");
                for (int hora = 0; hora <= 24; hora += 6) {
                    double c = sensorChuva.lerValor();
                    double r = sensorRio.lerValor();
                    histChuva.add(c);
                    histRio.add(r);
                    histUmidade.add(sensorUmidade.lerValor());
                    System.out.print("[" + hora + "h] Chuva: " + c + "mm | Rio: " + r + "m -> ");
                    verificarAlerta("Monitor 24h", c, territorio.getLimiarChuva(), "mm");
                }
            }
        }
        scanner.close();
    }

    public static void verificarAlerta(String sensor, double valor, double limite, String unidade) {
        if (valor >= limite * 1.25) {
            System.out.println("[CRÍTICO] " + sensor + ": " + valor + unidade + " ultrapassou muito o limite de " + limite + unidade);
        } else if (valor >= limite) {
            System.out.println("[ALERTA] " + sensor + ": " + valor + unidade + " estourou o limite de " + limite + unidade);
        } else if (valor >= limite * 0.85) {
            System.out.println("[ATENÇÃO] " + sensor + ": " + valor + unidade + " próximo do limite de " + limite + unidade);
        } else {
            System.out.println("[NORMAL] " + sensor + " dentro do esperado.");
        }
    }
}