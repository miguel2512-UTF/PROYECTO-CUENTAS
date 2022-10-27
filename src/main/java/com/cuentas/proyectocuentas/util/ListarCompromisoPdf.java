package com.cuentas.proyectocuentas.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.cuentas.proyectocuentas.model.Compromiso;


import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("views/compromiso/compromiso")
public class ListarCompromisoPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

                @SuppressWarnings("unchecked")
                List<Compromiso> compromiso = (List<Compromiso>)model.get("compromisos");


               //FUENTES
               Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.WHITE);
               Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.black);
               
              

                //HOJA HORIZONTAL
               document.setPageSize(PageSize.LETTER.rotate()); 

               //MARGENES TABLA
               document.setMargins(-30, -30, 40, 20);
               document.open();
               PdfPCell celda = null;
               
               //TITULO TABLA
               PdfPTable TablaTitulo = new PdfPTable(1);
              
               celda = new PdfPCell(new Phrase("REPORTE COMPROMISOS", fuenteTitulo));
               celda.setBorder(0);
               celda.setBackgroundColor(new Color(7,183,18));
               celda.setHorizontalAlignment(Element.ALIGN_CENTER);
               celda.setVerticalAlignment(Element.ALIGN_CENTER);
               celda.setPadding(30);

    
               TablaTitulo.addCell(celda);
               TablaTitulo.setSpacingAfter(30);

               //TITULO TABLA
                PdfPTable TablaCompromisos = new PdfPTable(10);
                TablaCompromisos.setWidths(new float[] {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f});

                celda = new PdfPCell(new Phrase("#", fuenteTituloColumnas));
                celda.setBackgroundColor(Color.lightGray);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(15);
                TablaCompromisos.addCell(celda);

                celda = new PdfPCell(new Phrase("Numero Factura", fuenteTituloColumnas));
                celda.setBackgroundColor(Color.lightGray);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(15);
                TablaCompromisos.addCell(celda);

                celda = new PdfPCell(new Phrase("Nombre Empresa", fuenteTituloColumnas));
                celda.setBackgroundColor(Color.lightGray);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(15);
                TablaCompromisos.addCell(celda);

                celda = new PdfPCell(new Phrase("Fecha Pago", fuenteTituloColumnas));
                celda.setBackgroundColor(Color.lightGray);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(15);
                TablaCompromisos.addCell(celda);

                celda = new PdfPCell(new Phrase("Fecha Suspension", fuenteTituloColumnas));
                celda.setBackgroundColor(Color.lightGray);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(15);
                TablaCompromisos.addCell(celda);

                celda = new PdfPCell(new Phrase("Metodo Pago", fuenteTituloColumnas));
                celda.setBackgroundColor(Color.lightGray);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(15);
                TablaCompromisos.addCell(celda);

                celda = new PdfPCell(new Phrase("Total", fuenteTituloColumnas));
                celda.setBackgroundColor(Color.lightGray);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(15);
                TablaCompromisos.addCell(celda);

                celda = new PdfPCell(new Phrase("Usuario", fuenteTituloColumnas));
                celda.setBackgroundColor(Color.lightGray);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(15);
                TablaCompromisos.addCell(celda);

                celda = new PdfPCell(new Phrase("Tipo Compromiso", fuenteTituloColumnas));
                celda.setBackgroundColor(Color.lightGray);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(15);
                TablaCompromisos.addCell(celda);

                celda = new PdfPCell(new Phrase("Estado", fuenteTituloColumnas));
                celda.setBackgroundColor(Color.lightGray);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(15);
                TablaCompromisos.addCell(celda);


       compromiso.forEach(compr->{
            TablaCompromisos.addCell(compr.getIdCom().toString());
            TablaCompromisos.addCell(compr.getNumeroFac());
            TablaCompromisos.addCell(compr.getNombreEm());
            TablaCompromisos.addCell(compr.getFecha());
            TablaCompromisos.addCell(compr.getFechaS());
            TablaCompromisos.addCell(compr.getMetodo());
            TablaCompromisos.addCell(compr.getTotal());
            TablaCompromisos.addCell(compr.getUsuario().getNombresUsuario());
            TablaCompromisos.addCell(compr.getTipocompromiso().getNombre());
            TablaCompromisos.addCell(compr.getEstadoCom());
        }); 

        //LLAMAR TITULO
        document.add(TablaTitulo); 

        /*LLAMAR CAMPOS*/
        document.add(TablaCompromisos); 
        
    }
    
}
