import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;

public class main extends JFrame {
    // Campos para entrada de números formatados (com separador decimal)
    private JFormattedTextField numero_A;
    private JFormattedTextField numero_B;
    // Botões para as operações matemáticas
    private JButton Dividir, Multiplicar, Subtrair, Fatorar, Somar, RaizQuadrada;
    // Label para mostrar resultado
    private JLabel Resultado;
    // Área de texto para mostrar histórico dos cálculos
    private JTextArea listaresultadosarea;
    // Lista que guarda os objetos de cálculo realizados
    private ArrayList<calculo> listaresultados;

    public main() {
        // Inicializa a lista de resultados
        listaresultados = new ArrayList<>();

        // Configurações básicas da janela
        setTitle("Calculadora");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel com layout para organizar os componentes da interface
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Espaçamento interno entre componentes
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Formatação para os campos numéricos - permite números formatados conforme localidade
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numero_A = new JFormattedTextField(numberFormat);
        numero_B = new JFormattedTextField(numberFormat);

        // Inicializa os botões para as operações
        Multiplicar = new JButton("Multiplicar");
        Subtrair = new JButton("Subtrair");
        Fatorar = new JButton("Fatorar");
        Somar = new JButton("Somar");
        RaizQuadrada = new JButton("Raiz Quadrada");
        Dividir = new JButton("Dividir");

        // Adiciona label e campo do primeiro número no painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(new JLabel("Primeiro numero (Para Raiz e Fatorar, use esse!): "), gbc);

        gbc.gridx = 1;
        painel.add(numero_A, gbc);

        // Adiciona label e campo do segundo número no painel
        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(new JLabel("Segundo numero: "), gbc);

        gbc.gridx = 1;
        painel.add(numero_B, gbc);

        // Adiciona os botões das operações no painel
        gbc.gridy = 2;
        gbc.gridx = 0;
        painel.add(Somar, gbc);

        gbc.gridx = 1;
        painel.add(Subtrair, gbc);

        gbc.gridx = 2;
        painel.add(Multiplicar, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        painel.add(Dividir, gbc);

        gbc.gridx = 1;
        painel.add(RaizQuadrada, gbc);

        gbc.gridx = 2;
        painel.add(Fatorar, gbc);

        // Label para mostrar o resultado, com tamanho e fonte definidos
        Resultado = new JLabel("Resultado: ");
        Resultado.setPreferredSize(new Dimension(500, 100));
        Resultado.setFont(new Font("Arial", Font.BOLD, 16));

        // Área de texto para mostrar o histórico dos cálculos - não editável
        listaresultadosarea = new JTextArea(5,2);
        listaresultadosarea.setEditable(false);

        // Adiciona a área de texto em um scroll para caso o texto ultrapasse o tamanho visível
        JScrollPane scroll = new JScrollPane(listaresultadosarea);

        // Adiciona os painéis e componentes principais na janela
        add(painel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(Resultado, BorderLayout.SOUTH);

        // --- Ações dos botões ---

        // Botão Multiplicar
        Multiplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pega os valores dos campos e converte para double
                double a = Double.parseDouble(numero_A.getText().replace(",", "."));
                double b = Double.parseDouble(numero_B.getText().replace(",", "."));

                // Cria um objeto da classe multiplicar
                multiplicar novocalculo = new multiplicar();
                // Adiciona o cálculo na lista de resultados
                listaresultados.add(novocalculo);

                // Calcula a multiplicação e mostra no label Resultado
                Resultado.setText("Resultado: " + novocalculo.calcular(a, b));

                // Adiciona o cálculo no histórico da área de texto
                listaresultadosarea.append("Multiplicação: " + a + " * " + b + " = " + String.format("%.2f", novocalculo.calcular(a, b)) + "\n");
                listaresultadosarea.append("\n");

                // Limpa os campos para próxima entrada
                numero_A.setText("");
                numero_B.setText("");
            }
        });

        // Botão Subtrair
        Subtrair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pega os valores dos campos e converte para double
                double a = Double.parseDouble(numero_A.getText());
                double b = Double.parseDouble(numero_B.getText());

                // Cria um objeto da classe subtracao
                subtracao novocalculo = new subtracao();
                // Adiciona o cálculo na lista
                listaresultados.add(novocalculo);

                // Calcula a subtração e atualiza o label Resultado
                Resultado.setText("Resultado: " + novocalculo.calcular(a, b));

                // Registra no histórico
                listaresultadosarea.append("Subtrção: " + a + " - " + b + " = " + String.format("%.2f", novocalculo.calcular(a, b)) + "\n");
                listaresultadosarea.append("\n");

                // Limpa os campos
                numero_A.setText("");
                numero_B.setText("");
            }
        });

        // Botão Somar
        Somar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a = Double.parseDouble(numero_A.getText());
                double b = Double.parseDouble(numero_B.getText());

                adicao novocalculo = new adicao();
                listaresultados.add(novocalculo);

                Resultado.setText("Resultado: " + novocalculo.calcular(a, b));

                listaresultadosarea.append("Soma: " + a + " + " + b + " = " + String.format("%.2f", novocalculo.calcular(a, b)) + "\n");
                listaresultadosarea.append("\n");

                numero_A.setText("");
                numero_B.setText("");
            }
        });

        // Botão Raiz Quadrada
        RaizQuadrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pega o número do primeiro campo (raiz usa só um número)
                double a = Double.parseDouble(numero_A.getText());

                // Cria objeto raiz e adiciona na lista
                raiz novocalculo = new raiz();
                listaresultados.add(novocalculo);

                // Calcula raiz e atualiza resultado
                Resultado.setText("Resultado: " + novocalculo.calcular(a));

                // Registra no histórico
                listaresultadosarea.append("Raiz de: " + a + " = " + String.format("%.2f", novocalculo.calcular(a)) + "\n");
                listaresultadosarea.append("\n");
                numero_A.setText("");
                numero_B.setText("");
            }
        });

        // Botão Dividir
        Dividir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a = Double.parseDouble(numero_A.getText());
                double b = Double.parseDouble(numero_B.getText());

                // Verifica se algum número é zero (evitar divisão por zero)
                if (a == 0 || b == 0) {
                    Resultado.setText("Divisor nulo! ");
                    return;
                }

                divisao novocalculo = new divisao();

                listaresultados.add(novocalculo);
                Resultado.setText("Resultado: " + novocalculo.calcular(a, b));

                listaresultadosarea.append("Divisão: " + a + " / " + b + " = " + String.format("%.2f", novocalculo.calcular(a, b)) + "\n");
                listaresultadosarea.append("\n");
                numero_A.setText("");
                numero_B.setText("");
            }
        });

        // Botão Fatorar
        Fatorar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pega texto do primeiro campo e troca vírgula por ponto
                String texto = numero_A.getText().replace(",", ".");
                // Valida se texto tem somente dígitos (número positivo)
                if (!texto.matches("\\d+")) {
                    Resultado.setText("Digite apenas numeros não negativos!");
                    numero_A.setText("");
                    numero_B.setText("");
                    return; // sai do método
                }
                // Converte para double para calcular fatorial
                double a = Double.parseDouble(texto);

                // Cria objeto fatorial e adiciona na lista
                fatorial novocalculo = new fatorial();
                listaresultados.add(novocalculo);

                // Calcula o fatorial
                double resultado = novocalculo.calcular(a);

                // Atualiza label Resultado
                Resultado.setText(("Resultado: " + resultado));

                // Adiciona resultado no histórico
                listaresultadosarea.append("Fatorial de: " + a +  "= " + String.format("%.2f", resultado) + "\n");
                listaresultadosarea.append("\n");
                // Limpa campos para próxima entrada
                numero_A.setText("");
                numero_B.setText("");
            }
        });

        // Torna a janela visível
        setVisible(true);
    }

    // Método main que inicia o programa
    public static void main(String[] args) {
        new main();
    }
}