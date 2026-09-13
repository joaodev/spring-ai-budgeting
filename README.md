# Spring AI Budgeting

Este projeto é uma API de controle financeiro com Spring Boot e Spring AI. Ele permite registrar gastos, consultar por categoria e usar IA para transformar uma descrição de compra em áudio em uma transação persistida automaticamente.

## O que o projeto faz

- Salva transações financeiras em banco MySQL;
- Organiza despesas por categoria: GROCERIES, PHARMA e AUTO;
- Lista transações por categoria via endpoint REST;
- Permite que o usuário envie um áudio em português e o sistema interprete a compra com IA;
- Usa ferramentas do Spring AI para o modelo chamar diretamente a lógica de negócio de criação e consulta.

Em outras palavras, a aplicação transforma um gasto falado em uma transação real no sistema, sem precisar que o usuário envie manualmente cada campo.

## Como executar a aplicação

### 1) Pré-requisitos

- Java 25
- Docker e Docker Compose
- Chave da OpenAI em variável de ambiente

### 2) Iniciar o banco de dados

```bash
docker compose up -d
```

### 3) Configurar a chave da OpenAI

```bash
export OPENAI_API_KEY=sua_chave_aqui
```

### 4) Rodar a aplicação

```bash
./gradlew bootRun
```

A API ficará disponível em:

- http://localhost:8080

## Melhoria implementada

A grande melhoria foi integrar o projeto com Spring AI para que a IA não apenas responda texto, mas também execute ações reais da aplicação.

O fluxo principal ficou assim:

- o usuário envia um áudio descrevendo uma compra;
- o modelo de transcrição do OpenAI converte o áudio para texto;
- o ChatClient usa as ferramentas `persist-transaction` e `list-transactions-by-category`;
- a IA escolhe a categoria correta e chama a lógica de negócio;
- a resposta é convertida em áudio novamente via TTS.

Isso deixa a aplicação mais natural para uso em mobile, assistentes ou cenários de produtividade.

## Tecnologias usadas

- Java 25
- Spring Boot 4
- Spring AI
- OpenAI GPT-4o-mini
- Whisper para transcrição de áudio
- TTS (text-to-speech) da OpenAI
- Spring Data JPA
- MySQL
- Docker Compose
- Gradle

## Como testar o fluxo principal

### 1) Criar uma transação via API REST

```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Supermercado",
    "amount": 120.50,
    "category": "GROCERIES"
  }'
```

### 2) Listar transações por categoria

```bash
curl http://localhost:8080/api/transactions/GROCERIES
```

### 3) Testar o fluxo com áudio

Prepare um arquivo de áudio em português, por exemplo com uma frase como:

```text
Gastei 45 reais no mercado hoje.
```

Em seguida, envie para a rota de IA:

```bash
curl -X POST http://localhost:8080/api/transactions/ai \
  -F "file=@/caminho/para/audio.wav" \
  --output resposta.mp3
```

O backend transcreve o áudio, interpreta a compra, grava a transação e devolve uma resposta em áudio.

## O que aprendi durante o desafio

- Como integrar IA em uma aplicação Java com Spring AI de forma prática;
- Como usar ferramentas (tools) para permitir que o modelo execute ações reais do sistema;
- Como combinar transcrição de áudio, processamento de linguagem e persistência em banco;
- A importância de separar domínio, casos de uso e infraestrutura para manter a aplicação organizada;
- Como criar APIs mais amigáveis e automatizadas usando inteligência artificial sem perder a clareza da arquitetura.

## Observações finais

Este projeto mostra um exemplo realista de como IA pode ser usada para reduzir esforço manual em tarefas financeiras, especialmente quando o usuário descreve um gasto em fala natural.
