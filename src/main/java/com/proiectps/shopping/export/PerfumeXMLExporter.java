package com.proiectps.shopping.export;

import com.proiectps.shopping.dto.PerfumeDTO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PerfumeXMLExporter {

    public static void export (List<PerfumeDTO> perfumes,String path) throws JAXBException, IOException {
        PerfumeListWrapper perfumeListWrapper = new PerfumeListWrapper(perfumes);
        JAXBContext context = JAXBContext.newInstance(PerfumeListWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        File file=new File(path);
        FileOutputStream outputStream=new FileOutputStream(file,false);
        marshaller.marshal(perfumeListWrapper,outputStream);
        outputStream.close();
    }
}
