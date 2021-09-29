window.addEventListener('load', function () {
    let tabla = document.querySelector('#info');
    let obtener = function(){
        const url = '/turnos';
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
                    <td class="id">${element.id}</td>
                    <td class="paciente">${element.paciente.id}</td>
                    <td>${element.paciente.nombre}</td>
                    <td class="odontologo">${element.odontologo.id}</td>
                    <td>${element.odontologo.nombre}</td>
                    <td class="fecha">${element.fecha}</td>
                    <td class="editar"><button>EDITAR</button></td>
                    <td class="eliminar"><button>ELIMINAR</button></td>
                </tr>`;
            } );
            confirmacionEliminar( );
            editar( );
        } );
    }
    obtener();

    let agregar = function( ) {
        const formulario = document.querySelector('#agregar');
        formulario.addEventListener('submit', function (event) {
            event.preventDefault();
            const formData = {
                paciente: { id: document.querySelector('#paciente').value },
                odontologo: { id: document.querySelector('#odontologo').value },
                fecha: document.querySelector('#fecha').value + " " + document.querySelector('#hora').value,
            };

            const url = '/turnos';
            const settings = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            }

            fetch(url, settings)
            .then(response => {
                response.json();
                obtener();
                resetUploadForm();
            })
        } );
    }
    agregar();

    function resetUploadForm( ) {
        document.querySelector('#paciente').value = "";
        document.querySelector('#odontologo').value = "";
        document.querySelector('#fecha').value = "";
        document.querySelector('#hora').value = "";
    }

    let confirmacionEliminar = function( ) {
        let btnEliminar = document.querySelectorAll('.eliminar');
        for(let i = 0; i < btnEliminar.length; i++) {
            btnEliminar[i].addEventListener("click", function() {
                let id = this.parentNode.id;
                if(confirm("Eliminar?")) {  
                    eliminar(id);
                }
            } );
        }
    }

    let eliminar = function(id) {
        const url = '/turnos/eliminar/'+ id;
        const settings = {
            method: 'DELETE'
        }
        fetch(url,settings)
        .then(response => {
            response.json();
            obtener();
        } )
    }

    let modificar = document.querySelector('#modificar');
    modificar.style.display = "none";

    let editar = function( ) {
        let btnModificar = document.querySelectorAll('.editar');
        for(let i = 0; i < btnModificar.length; i++) {
            btnModificar[i].addEventListener("click", function() {
                modificar.style.display = "block";
                document.querySelector('#id_modificar').value = this.parentNode.querySelector('.id').innerHTML;
                document.querySelector('#paciente_modificar').value = this.parentNode.querySelector('.paciente').innerHTML;
                document.querySelector('#odontologo_modificar').value = this.parentNode.querySelector('.odontologo').innerHTML;
                document.querySelector('#fecha_modificar').value = this.parentNode.querySelector('.fecha').innerHTML;
            } )
        }

        let formModificar = document.querySelector('#modificar form');
        formModificar.addEventListener('submit', function (event) {
            event.preventDefault();

            const formData = {
                id: document.querySelector('#id_modificar').value,
                paciente: { id: document.querySelector('#paciente_modificar').value },
                odontologo: { id: document.querySelector('#odontologo_modificar').value },
                fecha: document.querySelector('#fecha_modificar').value + " " + document.querySelector('#hora_modificar').value
            };
            
            const url = '/turnos/actualizar/';
            const settings = {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            }
            fetch(url,settings)
            .then(response => { 
                response.json();
                obtener();
                resetUploadForm();
                modificar.style.display = "none";
            } )
        } )
    }
} );