import axios from "axios";

export const analizarTexto = async (descripcion) => {
  try {
    const res = await axios.post("http://localhost:8080/API/nutricion/texto", {
      descripcion: descripcion,
    });

    console.log(res.data)
    return res.data

  } catch (error) {
    console.error("Error analizando texto", error);
  }
}

export const analizarFoto = async (formData) => {

  try {
    const res = await axios.post("http://localhost:8080/API/nutricion/foto", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
    
    console.log(res.data)
    return res.data
  } catch (error) {
    console.error("Error analizando foto", error);
  }

}