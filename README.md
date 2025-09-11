
# 📘 SYSMAN - Prueba Técnica

Este proyecto es una **prueba técnica** para la empresa **Sysman**.  
Consiste en una aplicación web que permite la **gestión de materiales** y está construida con las siguientes tecnologías.

---

## 📑 Tabla de contenidos
- [Descripción](#-descripción)
- [Tecnologías](#-tecnologías)
- [Funcionalidades](#-funcionalidades)
- [Arquitectura](#-arquitectura)
- [Instalación](#-instalación)
- [Uso](#-uso)
- [API Endpoints](#-api-endpoints)
- [Colección de Postman](#-colección-de-postman)
- [Tests](#-tests)
- [Autor](#-autor)

---

## 📝 Descripción
La aplicación permite **crear, editar, listar y buscar materiales** por diferentes criterios (ID, tipo, fecha de compra, ciudad).  
Además, incluye un módulo para gestionar y listar ciudades.

---

## 🛠️ Tecnologías
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-17-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-Collection-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

---

## 🚀 Funcionalidades
- Crear nuevos materiales
- Editar materiales existentes
- Listar todos los materiales
- Buscar materiales por:
  - **ID**
  - **Fecha de compra**
  - **Tipo**
  - **Ciudad**
- Listar todas las ciudades disponibles

---

## 🏗️ Arquitectura
El proyecto está dividido en dos módulos principales:

```
backend/   -> API REST con Spring Boot (Java 21)
frontend/  -> Aplicación Angular con Bootstrap 5
```

---

## ⚙️ Instalación

### Backend (Spring Boot)
```bash
cd backend
./mvnw spring-boot:run
```
La API quedará disponible en:
```
http://localhost:8080/sysman
```

### Frontend (Angular)
```bash
cd frontend
npm install
ng serve
```
Accede en el navegador a:
```
http://localhost:4200
```

---

## 📡 API Endpoints

| Método | Endpoint                                   | Descripción                          |
|--------|-------------------------------------------|--------------------------------------|
| GET    | `/sysman/materiales`                      | Listar todos los materiales          |
| GET    | `/sysman/materiales/{id}`                 | Buscar material por ID               |
| POST   | `/sysman/materiales/guardar`              | Crear un material                    |
| PUT    | `/sysman/materiales/actualizar/{id}`      | Editar un material                   |
| GET    | `/sysman/materiales/filtrar/{tipo}/{fecha}` | Filtrar materiales por tipo y fecha |
| GET    | `/sysman/materiales/ciudad/{idCiudad}`    | Filtrar materiales por ciudad        |
| GET    | `/sysman/ciudades`                        | Listar todas las ciudades            |

---

## 📦 Colección de Postman
Se incluye el archivo `SYSMAN.postman_collection.json` con todos los endpoints listos para probar.  
Puedes importarlo directamente en Postman y realizar las pruebas.

---

## 🧪 Tests
Para ejecutar los tests del backend:
```bash
cd backend
./mvnw test
```

---

## 👨‍💻 Autor
Prueba técnica desarrollada por **Jorge Caro** para el proceso de selección en **Sysman**.

