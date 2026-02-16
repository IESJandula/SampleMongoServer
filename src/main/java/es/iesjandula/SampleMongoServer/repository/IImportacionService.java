package es.iesjandula.SampleMongoServer.repository;

public interface IImportacionService 
{
    void importarProductosDesdeJson(String rutaArchivo);
}