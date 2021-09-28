window.addEventListener('load', function () {
    let tabla = document.querySelector('#info');
    let obtener = function(){
        const url = '/pacientes';
        const settings = {
            method: 'GET'
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            tabla.innerHTML = "";
            data.forEach(element => {
                tabla.innerHTML +=
                `<tr id=${element.id}>
                    <td>${element.id}</td>
                    <td>${element.nombre}</td>
                    <td>${element.apellido}</td>
                    <td>${element.dni}</td>
                    <td>${element.domicilio}</td>
                    <td>${element.fechaDeAlta}</td>
                    <td class="editar"><button>EDITAR</button></td>
                    <td class="eliminar"><button>ELIMINAR</button></td>
                </tr>`;
            } );
            eliminar();
            editar();
        } );
    }
    obtener();

    const formulario = document.querySelector('#agregar');
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            domicilio: document.querySelector('#domicilio').value,
            fechaDeAlta: document.querySelector('#fechaDeAlta').value,
        };

        const url = '/pacientes';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            document.querySelector('#response').style.display = "block";
            resetUploadForm();
            obtener();
        } )
        .catch(error => {
            document.querySelector('#response').style.display = "block";
            resetUploadForm();
        } )
    } );

    function resetUploadForm( ) {
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#domicilio').value = "";
        document.querySelector('#fechaDeAlta').value = "";
    }

    let eliminar = function() {
        let btnEliminar = document.querySelector('.eliminar');
        btnEliminar.addEventListener("click", function() {
            let id = this.parentNode.id;
            const url = '/pacientes/eliminar/'+ id;
            const settings = {
                method: 'DELETE'
            }
            fetch(url,settings)
            .then(response => response.json())
            this.parentNode.remove();
        } )
    }

    let modificar = document.querySelector('#modificar');
        modificar.style.display = "none";

    let editar = function() {
        let btnModificar = document.querySelector('.editar');
        btnModificar.addEventListener("click", function() {
            let id = this.parentNode.id;
            modificar.style.display = "block";
            modificar.addEventListener('submit', function (event) {
                event.preventDefault();
                console.log(id);
                const formData = {
                    id: id,
                    nombre: document.querySelector('#nombre_modificar').value,
                    apellido: document.querySelector('#apellido_modificar').value,
                    dni: document.querySelector('#dni_modificar').value,
                    domicilio: document.querySelector('#domicilio_modificar').value,
                    fechaDeAlta: document.querySelector('#fechaDeAlta_modificar').value,
                };
                
                const url = '/pacientes/actualizar/';
                const settings = {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formData)
                }
                fetch(url,settings)
                .then(response => response.json())
                .then(data => {
                    obtener();
                })
                modificar.style.display = "none";
            } )
        } )
    }
} );