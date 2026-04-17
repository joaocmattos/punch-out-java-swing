# 🥊 Java Punch-Out MVP

Um protótipo funcional de jogo de boxe inspirado no clássico *Mike Tyson's Punch-Out!!*, desenvolvido inteiramente em Java utilizando a biblioteca Swing.

## 🚀 Funcionalidades Implementadas

O projeto foca na lógica central de combate e no "Game Feel" (sensação de impacto):

- **IA do Oponente (Mike Tyson):** Sistema de rotina de ataque com estados de preparação (winding up), ataque e atordoamento.
- **Mecânicas do Jogador (Little Mac):**
  - Esquiva com frames de invencibilidade.
  - Sistema de ataque condicional.
  - **Sistema de Stamina:** O jogador precisa gerenciar o fôlego; ao esgotar, entra em estado de exaustão e não pode atacar.
- **Feedback Visual (Game Juice):**
  - **Camera Shake:** Tremor de tela aleatório baseado na intensidade do impacto.
  - **Hit Flash:** Personagens piscam em branco puro ao receber dano para indicar colisão.
- **Interface (UI):** Barras de vida dinâmicas e barra de stamina com troca de cores por estado.
- **Game Flow:** Sistema de Game Over e Vitória com overlay de tela escurecida.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Interface Gráfica:** Swing / AWT (Graphics2D)
- **Arquitetura:** Lógica baseada em Estados (FighterState) e Timers para controle de tempo de jogo.

## 🕹️ Como Executar

Atualmente o projeto é um MVP (Minimum Viable Product) focado em lógica. Para rodar:

1. Clone o repositório.
2. Compile as classes a partir da `Main` (ou `GameWindow`).
3. Comandos:
   - **Barra de Espaço:** Soco.
   - **Setas (Esquerda/Direita):** Esquiva.

---
*Desenvolvido como estudo de lógica de jogos e manipulação de Graphics2D em Java.*
