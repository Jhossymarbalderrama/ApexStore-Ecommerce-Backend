# 📦 API | Apex Store E-Commerce 🛒
<p>API dedicada para página web e-commerce AApex Store.</p>
<p>📌 Demo: <a href='https://e-commerce-ac291.web.app/' target='_blank'>↗️ Web Apex Store / E-Commerce 👈<a/></p>
<p>📌 Repositorio Frontend: <a href='https://github.com/Jhossymarbalderrama/ApexStore-Ecommerce-Front' target='_blank'>↗️ Go Repository 👈<a/></p>
  
<p align="center">
  <img src='https://github.com/Jhossymarbalderrama/ApexStore-Ecommerce-Backend/assets/52534649/07ab3f08-b107-4eae-8374-c973a7ea1f87'/>
</p>

<hr>

### API URL |Documentación
<p>Documentación de Swagger: <a href="https://apexstore-ecommerce-backend.onrender.com/swagger-ui/index.html" target="_blank"> Swagger API</a></p>
<p>Local: http://localhost:{port}/</p>
<p>Render: https://apexstore-ecommerce-backend.onrender.com/ </p>

### Auth

| Method   | URL                                      | Descripción                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `POST`   | `/auth/login`                            | Login de usuarios                        |
| `POST`   | `/auth/register`                         | Registro de usuarios                     |

### 📦 Productos

| Method   | URL                                      | Descripción                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `/api/product/list`                      | Listar Productos                         |
| `GET`    | `/api/product/get/{id}`                  | Detalle Producto                         |
| `POST`   | `/api/product/add`                       | Alta de producto                         |
| `DELETE` | `/api/product/delete/{id}`               | Baja de producto                         |
| `PUT`    | `/api/product/update`                    | Modificación de producto                 |

### 💼 Usuarios

| Method   | URL                                      | Descripción                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `/api/user/list`                         | Listar Usuarios                          |
| `GET`    | `/api/user/get/{id}`                     | Detalle Usuario                          |
| `GET`    | `/api/user/get/detail/{username}`        | Detalle Usuario                          |
| `DELETE` | `/api/user/delete/{id}`                  | Baja de Usuario                          |
| `PUT`    | `/api/user/update`                       | Modificación de Usuario                  |

### 📑 Facturas

| Method   | URL                                      | Descripción                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `/api/factura/list`                      | Listar Facturas                          |
| `GET`    | `/api/factura/list/{id}`                 | Listar Facturas de Usuario               |
| `GET`    | `/api/factura/get/{id}`                  | Detalle Factura                          |
| `POST`   | `/api/factura/add`                       | Alta Factura                             |
| `POST`   | `/api/factura/cancel`                    | Cancelar Factura                         |
| `PUT`    | `/api/factura/update`                    | Modificar Factura                        |

### 🛵 Delivery

| Method   | URL                                      | Descripción                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `/api/delivery/list`                     | Listar Deliverys                         |
| `GET`    | `/api/delivery/get/{id}`                 | Detalle Delivery                         |
| `POST`   | `/api/delivery/add`                      | Alta de Delivery                         |
| `DELETE` | `/api/delivery/delete/{id}`              | Baja de Delivery                         |
| `PUT`    | `/api/delivery/update`                   | Modificación de Delivery                 |

### 📊 Charts Data

| Method   | URL                                      | Descripción                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `GET`    | `/api/chart/listTotalIngresosXMes`       | Lista total ingresos por mes             |
| `GET`    | `/api/chart/listTop5ProductosVendidos`   | Lista top 5 Productos mas vendidos       |
| `GET`    | `/api/chart/listCantUserXRole`           | Lista cantidad de Usuarios por Role      |
| `GET`    | `/api/chart/listCantidadDeFacturasMes`   | Lista cantidad de Facturas por mes       |
| `GET`    | `/api/chart/listAltaProductosXMes`       | Lista altas de Productos por mes         |
| `GET`    | `/api/chart/getDataCharts`               | Lista de datos de todos los charts       |


## 📌 🖥️ Requisitos y Instalación del Proyecto 📱

###### Tener instalado Apache NetBeans IDE 17

| Nombre | Version | Documentación |
| --- | --- | --- |
| `Apache NetBeans` |  IDE 17 | https://netbeans.apache.org/front/main/download/archive/ |
| `Java` | 17 | https://www.java.com/es/download/ |
| `Java` | JDK-17 | https://www.oracle.com/ar/java/technologies/downloads/#java17 |

###### Levantar API
Luego de tener una copia del repositorio local, correr los siguentes comandos en la terminal del proyecto (API). 

# Instalar dependencias
`` Clean and Build ``
`` Build with Dependencies ``

<p align="left">
  <img src='https://github.com/Jhossymarbalderrama/ApexStore-Ecommerce-Backend/assets/52534649/f2273dd4-4925-486f-9400-04d5ccc1eb7a'/>
</p>

# Correr el proyecto
<p align="left">
  <img src='https://github.com/Jhossymarbalderrama/ApexStore-Ecommerce-Backend/assets/52534649/676802cf-cc14-4467-9b53-2cb28e5411e7'/>
</p>

<hr>

## 📌 🖥️ Tecnologías - Documentación  📱

###### Backend - (Hosting Render)

| Nombre | Version | Documentación |
| --- | --- | --- |
| `Apache NetBeans` |  IDE 17 | https://netbeans.apache.org/front/main/download/archive/ |
| `Java` | 17 | https://www.java.com/es/download/ |
| `Java` | JDK-17 | https://www.oracle.com/ar/java/technologies/downloads/#java17 |
  
###### Frontend - (Hosting Firebase)
| Nombre | Version | Documentación | Descripción |
| ----- | --- | --- | --- |
| `Angular CLI` |  v16.1.8 | https://angular.io/cli |
| `Firebase` | v13.4.0 | https://firebase.google.com/docs/web/setup?hl=es-419 |
| `Tailwind Css` |  v3.4.3 | https://tailwindcss.com/docs/installation |
| `Flowbite` |  v2.2.0 | https://flowbite.com/docs/getting-started/quickstart/#getting-started |
| `Apexcharts` | v3.48.0 | https://flowbite.com/docs/plugins/charts/ | (Recomendacion de Flowbite) |
| `primeng` |  v16.5.1 | https://primeng.org/toast |
| `fontawesome` |  v6.5.0 | https://fontawesome.com/ |

###### Base de datos - (Hosting Clever-Cloud)
| Nombre | Version | Documentación |
| --- | --- | --- |
| `MySQL` |  - |  |
| `Apache` | - | |

<hr>

# 💻 Navegación de páginas
<p>📌 Demo: <a href='https://e-commerce-ac291.web.app/' target='_blank'>↗️ Web Apex Store / E-Commerce 👈<a/></p>

## 🏠 Home 
<p align="center">
  
  ![Apex Store Home](https://github.com/user-attachments/assets/f03dd299-cb15-45d0-a530-c6d65a90b106)
</p>

## 🏪 Tienda 
<p align="center">
  <img src='https://github.com/user-attachments/assets/f2f70973-314e-44e6-85f2-637352c7382e'/>
</p>

## 🏬 Nosotros  
<p align="center">
  <img src='https://github.com/user-attachments/assets/7c49c618-0b4e-4a62-aae3-0ae23e772548'/>
</p>

## 📠 Contacto
<p align="center">
  <img src='https://github.com/user-attachments/assets/7e1ccac4-041e-4b99-8962-76ab8e0212db'/>
</p>

## 🧑‍💼 Dashboard Administrador de Web
<p align="center">
  <img src='https://github.com/user-attachments/assets/dff770fe-c65c-4d9e-8880-fc9c7e69063c'>
</p>

##  🖥️ Responsive Página Web 📱

| Macbooks y Tablets  | Smartphones |
| ------------- | ------------- |
| <img src='https://github.com/user-attachments/assets/9b7a7337-16c2-4613-867b-08fd80007c7d' width="750px" height="auto" /> |  <img src='https://github.com/user-attachments/assets/1d4c5e7c-b0e3-4624-8efd-3316ea9a48f9' width="100%" height="100%" />  |


