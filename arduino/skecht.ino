#include <WiFi.h>
#include <WiFiClientSecure.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>

// WiFi do Wokwi
const char* ssid = "Wokwi-GUEST";
const char* password = "";

const char* server = "https://territories-recruitment-reaction-receptors.trycloudflare.com";

// Pinos
const int btnEntrada = 16;
const int btnSaida = 17;
const int ledVerde = 12;
const int ledVermelho = 13;

WiFiClientSecure client;

// Controle de debounce
unsigned long lastClickEntrada = 0;
unsigned long lastClickSaida = 0;
const int debounceDelay = 300;

void setup() {

  Serial.begin(115200);

  pinMode(btnEntrada, INPUT_PULLUP);
  pinMode(btnSaida, INPUT_PULLUP);
  pinMode(ledVerde, OUTPUT);
  pinMode(ledVermelho, OUTPUT);
  digitalWrite(ledVerde, LOW);
  digitalWrite(ledVermelho, LOW);

  conectarWiFi();

  client.setInsecure();
}

void loop() {
  checarEntrada();
  checarSaida();
}

// =========================
// WIFI
// =========================
void conectarWiFi() {
  WiFi.begin(ssid, password);

  Serial.print("Conectando no WiFi");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("\nWiFi conectado!");
}

// =========================
// BOTÃO ENTRADA
// =========================
void checarEntrada() {
  if (digitalRead(btnEntrada) == LOW &&
      millis() - lastClickEntrada > debounceDelay) {

    Serial.println("BOTÃO ENTRADA");
    enviarEvento("/entrada");
    lastClickEntrada = millis();
  }
}

// =========================
// BOTÃO SAÍDA
// =========================
void checarSaida() {
  if (digitalRead(btnSaida) == LOW &&
      millis() - lastClickSaida > debounceDelay) {

    Serial.println("BOTÃO SAÍDA");
    enviarEvento("/saida");
    lastClickSaida = millis();
  }
}

// =========================
// REQUISIÇÃO HTTP
// =========================
void enviarEvento(String rota) {

  if (WiFi.status() != WL_CONNECTED) {
    Serial.println("WiFi desconectado!");
    return;
  }

  HTTPClient https;

  String url = String(server) + rota;

  Serial.println("→ Enviando: " + url);

  https.begin(client, url);
  int httpCode = https.POST("");

  if (httpCode > 0) {

    Serial.println("Código HTTP: " + String(httpCode));

    String payload = https.getString();
    Serial.println("Resposta:");
    Serial.println(payload);

    interpretarResposta(payload);

  } else {
    Serial.println("Erro na requisição: " + String(httpCode));
  }

  https.end();
}

// =========================
// TRATAR JSON
// =========================
void interpretarResposta(String payload) {

  DynamicJsonDocument doc(1024);
  DeserializationError error = deserializeJson(doc, payload);

  if (error) {
    Serial.println("Erro ao ler JSON!");
    return;
  }

  bool permitido = doc["permitido"];

  if (permitido) {
    digitalWrite(ledVerde, HIGH);
    digitalWrite(ledVermelho, LOW);
    Serial.println("✅ Entrada permitida");
  } else {
    digitalWrite(ledVerde, LOW);
    digitalWrite(ledVermelho, HIGH);
    Serial.println("⛔ Lotado");
  }
}