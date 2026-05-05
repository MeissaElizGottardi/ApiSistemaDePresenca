# 🚪 Controle de Presença IoT

Sistema completo de controle de acesso utilizando:

- ESP32 (Wokwi)
- Backend com Spring Boot
- Frontend Web (HTML, CSS, JS)
- Integração via HTTP

---

## 📌 Funcionalidades

- Entrada e saída de pessoas via botão
- Controle de limite de capacidade
- Bloqueio automático quando lotado
- Indicação por LED:
  - 🟢 Verde: entrada permitida
  - 🔴 Vermelho: bloqueado
- Painel web com:
  - Quantidade de pessoas
  - Limite configurado
  - Status do ambiente
  - Barra de progresso
  - Gráfico em tempo real

---

## 🛠️ Tecnologias

- Java 17
- Spring Boot
- MySQL ou H2
- HTML / CSS / JavaScript
- ESP32 (Wokwi)
- Cloudflare

---

## ESP32 do projeto

![Arduino](Imagens/sistemadepresenca.png)

## ▶️ Como executar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/SEU-USUARIO/controle-presenca-iot.git
cd controle-presenca-iot
```

2. Crie o banco no MySQL:

```sql
CREATE DATABASE sistemaacesso;
```

3. Configure o `application.properties` com seu usuário e senha do MySQL.

4. Execute o backend:

```bash
mvn spring-boot:run
```

5. (Opcional) Exponha a API com Cloudflare:

```bash
cloudflared tunnel --url http://localhost:8080
```

6. Acesse:

* API: http://localhost:8080/status
* Painel: abra o `index.html`


