# Territory Predictive Insights — Monitoramento de Alagamentos (CitizenTech)

É uma plataforma de monitoramento territorial desenvolvida em Java aplicando os pilares da Programação Orientada a Objetos (POO) para apoiar comunidades e órgãos locais na prevenção de riscos de alagamento causados por chuvas intensas.

## Público Beneficiado
* **Quem sofre o problema:** Moradores, comerciantes e lideranças comunitárias de bairros próximos a córregos e áreas sujeitas a enchentes (representados no sistema pela região *Zona Leste - Vila Verde*).
* **Qual decisão o sistema apoia:** Ajuda agentes comunitários e a defesa civil local a identificar antecipadamente a elevação do volume de chuva e do nível do rio, apoiando decisões preventivas como limpeza rápida de bueiros, bloqueio preventivo de vias alagáveis e aviso antecipado aos moradores.

## Estrutura do Projeto e Conceitos de POO Aplicados
* `ComponenteTerritorial.java` **(Classe Abstrata):** Define a estrutura base dos equipamentos instalados no território (`id`, `nome`, `regiao`, `ativo`), métodos concretos (`ativar()`, `desativar()`) e o método abstrato `verificarStatus()`.
* `EstacaoMonitoramento.java` **(Herança):** Subclasse concreta de `ComponenteTerritorial` que implementa `verificarStatus()` e identifica o ponto de referência da estação.
* `Sensor.java` **(Interface):** Contrato obrigatório para todos os sensores (`lerValor()`, `verificarFuncionamento()`, `retornarTipo()`, `retornarUnidade()`).
* `SensorChuva.java`, `SensorNivelRio.java` e `SensorUmidade.java` **(Implementação de Interface):** Implementam o contrato `Sensor` simulando leituras realistas com `Math.random()`.
* `EstrategiaPrevisao.java` **(Classe Abstrata):** Define o contrato base para cálculos preditivos sobre o histórico de leituras (`ArrayList<Double>`).
* `PrevisaoMediaMovel.java` e `PrevisaoComparacao.java` **(Herança e Polimorfismo):** Herdam de `EstrategiaPrevisao`, utilizam `super()` e sobrescrevem `prever()` com regras distintas (média das últimas *N* leituras e projeção por comparação entre período atual e anterior).
* `DadosTerritorio.java` **(Encapsulamento):** Protege dados sensíveis da comunidade com atributos privados, validações nos *setters* e exigência de código de acesso (`1234`) para alteração dos limiares de alerta.
* `SistemaMonitoramento.java` **(Classe Principal):** Reúne o menu interativo no console, classificação automática de alertas (`ATENÇÃO`, `ALERTA` e `CRÍTICO`) e simulação de 24 horas.

## Como Compilar e Executar
Certifique-se de ter o **Java JDK (17 ou superior)** instalado. No terminal, dentro da pasta com os arquivos `.java`, execute:

```bash
javac *.java
java SistemaMonitoramento


## Reflexão Crítica

1. **Quando o sistema deve se calar?**
   Quando a base possui poucas amostras (por exemplo, menos de 5 leituras consecutivas) ou quando um sensor apresenta falha operacional. Com poucas leituras, uma única medição isolada distorce tanto a média móvel quanto a comparação entre períodos, podendo gerar alarmes falsos ou esconder um risco real. Por isso, o sistema exibe um aviso explícito de baixa confiabilidade sempre que o histórico tem menos de 5 registros.

2. **O que os dados não representam?**
   Uma única estação de monitoramento por região não captura microclimas nem problemas estruturais pontuais, como um bueiro entupido em uma rua específica, obras irregulares na margem do córrego ou a vulnerabilidade individual de cada moradia. Os números mostram uma tendência geral da área, mas não substituem a vistoria presencial no território.

3. **Por que o alerta apoia a decisão da pessoa, em vez de decidir por ela?**
   Evacuar uma área ou interditar uma via envolve custos sociais, logísticos e humanos que dependem do contexto local (como horários de pico, presença de idosos ou escolas próximas). O sistema transforma leituras brutas em sinais claros (`ATENÇÃO`, `ALERTA`, `CRÍTICO`), mas a decisão final precisa ser tomada por pessoas capacitadas e lideranças locais que conhecem a realidade do bairro.

## Uso de IA
* **Onde usamos:** Utilizamos ferramentas de IA generativa como apoio de estudo para revisar a sintaxe de classes abstratas, interfaces e encapsulamento em Java, além de estruturar o menu de console e revisar a organização do `README.md`.
* **Como revisamos:** Todo o código foi dividido entre os integrantes, compilado e testado localmente no VS Code, ajustando validações, arredondamentos e mensagens de alerta para garantir que cada membro compreendesse integralmente a lógica implementada.

## Divisão de Tarefas da Equipe
* **Kaue Souza Rodrigues (RM 567716):** Modelagem territorial e sensores (`ComponenteTerritorial.java`, `EstacaoMonitoramento.java`, `Sensor.java`, `SensorChuva.java`, `SensorNivelRio.java`, `SensorUmidade.java`).
* **Gabriel Kenishi Furuzawa (RM 568245):** Estratégias de previsão com herança (`EstrategiaPrevisao.java`, `PrevisaoMediaMovel.java`, `PrevisaoComparacao.java`).
* **Pedro Henrique Camacho de Alencar (RM 568071):** Encapsulamento, sistema principal de alertas, simulação 24h e documentação (`DadosTerritorio.java`, `SistemaMonitoramento.java`, `README.md`).