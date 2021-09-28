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
                    <td>${element.id}</td>
                    <td>${element.paciente.id}</td>
                    <td>${element.odontologo.id}</td>
                    <td>${element.fecha}</td>
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
        const paciente = { id: document.querySelector('#paciente').value };
        const odontologo = { id: document.querySelector('#odontologo').value };
        console.log(paciente);
        const formData = {
            paciente: paciente,
            odontologo: odontologo,
            fecha: document.querySelector('#fecha').value,
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
        .then(response => response.json())
        .then(data => {
            console.log(data);
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
        document.querySelector('#paciente').value = "";
        document.querySelector('#odontologo').value = "";
        document.querySelector('#fecha').value = "";
    }

    let eliminar = function() {
        let btnEliminar = document.querySelector('.eliminar');
        btnEliminar.addEventListener("click", function() {
            let id = this.parentNode.id;
            const url = '/turnos/eliminar/'+ id;
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
                    paciente: document.querySelector('#paciente_modificar').value,
                    odontologo: document.querySelector('#odontologo_modificar').value,
                    fecha: document.querySelector('#fecha_modificar').value,
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
                .then(response => response.json())
                .then(data => {
                    obtener();
                })
                modificar.style.display = "none";
            } )
        } )
    }
} );