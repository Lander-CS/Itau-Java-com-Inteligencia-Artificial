# ☕ Bootcamp Itaú - Java com Inteligência Artificial (DIO)

Repositório dedicado ao registro do meu aprendizado, anotações teóricas e resolução de exercícios práticos durante o **Bootcamp Itaú - Java com Inteligência Artificial**, promovido pela [DIO.me](https://www.dio.me/) em parceria com o **Itaú**.

---

## 🎯 Objetivos de Aprendizado

- [x] Configuração de ambiente e entendimento do Classpath no Java
- [x] Entrada e saída de dados no console (`Scanner`, `print`, `println`, `printf`)
- [x] Gerenciamento de recursos e prevenção de vazamentos de memória (`Resource leak`)
- [x] Domínio dos operadores fundamentais do Java
- [x] Resolução de desafios práticos com regras de negócio simuladas
- [ ] Estruturas de repetição (`while`, `for`, `do-while`)
- [ ] Programação Orientada a Objetos (POO)
- [ ] Integrações com IA e Spring Boot

---

## 📚 Conceitos Praticados

### 1. Entrada e Saída de Dados
- **`Scanner` (`java.util.Scanner`)**: Captura de dados digitados pelo usuário via terminal (`System.in`).
  - `.nextLine()`: Leitura de textos (`String`).
  - `.nextInt()`: Leitura de inteiros (`int`).
  - `.nextDouble()`: Leitura de números com ponto flutuante (`double`).
- **Gerenciamento de Recursos**:
  - Toda instância de `Scanner` aberta sobre fluxos do sistema deve ser explicitamente finalizada com `scanner.close()` ao término do uso para evitar avisos de *Resource leak*.

### 2. Diferenças entre `print`, `println` e `printf`

| Método | Pula linha no final? | Permite formatação de variáveis? | Exemplo |
| :--- | :---: | :---: | :--- |
| **`print()`** | ❌ Não | ❌ Não (usa `+`) | `System.out.print("Olá ");` |
| **`println()`** |  Sim | ❌ Não (usa `+`) | `System.out.println("Olá Mundo!");` |
| **`printf()`** | ❌ Não (usa `%n`) |  Sim (máscaras) | `System.out.printf("Nome: %s \| Nota: %.2f%n", nome, nota);` |

- **Principais máscaras do `printf`**:
  - `%s`: Texto (`String`)
  - `%d`: Inteiro (`int`, `long`)
  - `%.2f`: Decimal com 2 casas (`double`, `float`)
  - `%n`: Quebra de linha multiplataforma

---

### 3. Operadores em Java

- **Aritméticos**: `+` (adição), `-` (subtração), `*` (multiplicação), `/` (divisão), `%` (módulo / resto da divisão inteira).
- **Relacionais**: `==` (igual), `!=` (diferente), `>` (maior), `<` (menor), `>=` (maior ou igual), `<=` (menor ou igual).
- **Lógicos**:
  - `&&` (*AND* / E): Verdadeiro se ambas as condições forem verdadeiras.
  - `||` (*OR* / OU): Verdadeiro se ao menos uma condição for verdadeira.
  - `!` (*NOT* / NÃO): Inversão lógica (ex: `!temRestricao`).
- **Atribuição Composta e Unários**:
  - `+=`, `-=`, `*=`, `/=`: Atualizam o valor aplicando a operação.
  - `++`, `--`: Incremento e decremento unitário.
- **Operador Ternário (`? :`)**:
  - Substitui condicionais simples de atribuição:
    ```java
    String status = aprovado ? "APROVADO" : "REPROVADO";
    ```

---

## 🛠️ Exercícios Desenvolvidos

A estrutura de código está organizada em pacotes modulares dentro de `src/main/java/`:

```text
src/main/java/
├── exercicio1/
│   └── Exercicio1.java    # Calculadora de Média com formatação printf
├── exercicio2/
│   └── Exercicio2.java    # Sistema Bancário de Aprovação de Empréstimo (Itaú)
└── exercicio3/
    └── Exercicio3.java    # Verificador Par/Ímpar, Módulo e Acumulador de Pontos
```

### [Exercício 1: Calculadora de Média Escolar](src/main/java/exercicio1/Exercicio1.java)
- **Foco**: Entrada via `Scanner`, média aritmética com precedência de operadores e saída formatada com `printf` e `%.2f`.
- **Destaque**: Utilização de `var` para inferência de tipo moderno no Java.

### [Exercício 2: Análise de Crédito Bancário](src/main/java/exercicio2/Exercicio2.java)
- **Foco**: Simulação de regras de concessão de crédito do Itaú.
- **Regra de negócio**:
  - Idade entre 18 e 65 anos (`idade >= 18 && idade <= 65`).
  - Renda mensal mínima de R$ 2.500,00 (`renda >= 2500.00`).
  - Ausência de restrição cadastral (`!temRestricao`).
- **Destaque**: Uso do operador ternário para decisão de mensagem em linha única.

### [Exercício 3: Verificador Par/Ímpar com Pontuação](src/main/java/exercicio3/Exercicio3.java)
- **Foco**: Uso do operador de resto `%` para determinar paridade (`numero % 2 == 0`).
- **Regras aplicadas**:
  - Par: `pontos += 20`
  - Ímpar: `pontos -= 10`
  - Bônus para números > 100: `pontos++`

---

## 💡 Aprendizados e Boas Práticas Anotadas

1. **Clean Code em Nomenclaturas**:
   - Evitar acentos e caracteres especiais em nomes de variáveis (ex: usar `ehPar` ou `isPar` ao invés de `éPar`) para garantir compatibilidade com qualquer padrão de codificação e pipeline CI/CD.
2. **Robustez em Entradas do Usuário**:
   - Para leitura de opções em sistemas reais, preferir ler texto com `.trim().toLowerCase()` e validar contra coleções/conjuntos (`Set.of(...)`), evitando exceções de formato causadas por `nextBoolean()`.
3. **Estrutura de Pastas e Classpath**:
   - Códigos Java em projetos estruturados devem sempre residir dentro de `src/main/java` para que a IDE e os compiladores reconheçam os arquivos no *classpath* corretamente.

---

## 🚀 Como Executar os Exercícios

Com o Java 21 instalado, você pode executar qualquer exercício pelo terminal na raiz do projeto:

```bash
# Executar Exercício 1
java src/main/java/exercicio1/Exercicio1.java

# Executar Exercício 2
java src/main/java/exercicio2/Exercicio2.java

# Executar Exercício 3
java src/main/java/exercicio3/Exercicio3.java
```

---
*Estudos realizados por mim durante o Bootcamp Itaú - Java com IA na plataforma DIO.me.*
