package com.cuentas.proyectocuentas.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.cuentas.proyectocuentas.model.Prestamo;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

@Component("views/prestamo/prestamo")
public class ListarPrestamoPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

                Integer user = Integer.parseInt(request.getParameter("usuario"));
                Integer estado = Integer.parseInt(request.getParameter("estado"));

            @SuppressWarnings("unchecked")
            List<Prestamo> prestamos =(List<Prestamo>) model.get("prestamos");

            List<Prestamo> prestamo = new ArrayList<Prestamo>();

            Iterator<Prestamo> i = prestamos.iterator();

            while (i.hasNext()) {
                Prestamo iterar = i.next();
                if (user == 0 && estado == 0) {
                    prestamo.add(iterar);
                } else if (user!=0) {
                    if (iterar.getUsuario().getIdUsuario()== user) {
                        prestamo.add(iterar);
                    }
                }
                if (estado==2) {
                    if (iterar.getEstadoPrestamo().equals("Activo")) {
                        prestamo.add(iterar);
                    }
                }
                if (estado==1) {
                    if (iterar.getEstadoPrestamo().equals("Inactivo")) {
                        prestamo.add(iterar);
                    }
                }
            }

         // FUENTES
         Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.WHITE);
         Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.black);
 
         // HOJA HORIZONTAL
         document.setPageSize(PageSize.LETTER.rotate());
 
         // MARGENES TABLA
         document.setMargins(-30, -30, 40, 20);
         document.open();
         PdfPCell celda = null;
 
         // TITULO TABLA
         PdfPTable TablaTitulo = new PdfPTable(1);
 
         celda = new PdfPCell(new Phrase("REPORTE PRESTAMOS", fuenteTitulo));
         celda.setBorder(0);
         celda.setBackgroundColor(new Color(7, 183, 18));
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(30);
 
         TablaTitulo.addCell(celda);
         TablaTitulo.setSpacingAfter(30);
 
         // TITULO TABLA
         PdfPTable TablaPrestamos = new PdfPTable(11);
         TablaPrestamos.setWidths(new float[] { 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f });
 
         celda = new PdfPCell(new Phrase("Id", fuenteTituloColumnas));
         celda.setBackgroundColor(Color.lightGray);
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(15);
         TablaPrestamos.addCell(celda);
 
         celda = new PdfPCell(new Phrase("Documento Prestamista", fuenteTituloColumnas));
         celda.setBackgroundColor(Color.lightGray);
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(15);
         TablaPrestamos.addCell(celda);
 
         celda = new PdfPCell(new Phrase("Nombre Prestamista", fuenteTituloColumnas));
         celda.setBackgroundColor(Color.lightGray);
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(15);
         TablaPrestamos.addCell(celda);
 
         celda = new PdfPCell(new Phrase("Nombre Usuario", fuenteTituloColumnas));
         celda.setBackgroundColor(Color.lightGray);
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(15);
         TablaPrestamos.addCell(celda);
 
         celda = new PdfPCell(new Phrase("Fecha de Pago Oportuno", fuenteTituloColumnas));
         celda.setBackgroundColor(Color.lightGray);
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(15);
         TablaPrestamos.addCell(celda);
 
         celda = new PdfPCell(new Phrase("Valor del Préstamos", fuenteTituloColumnas));
         celda.setBackgroundColor(Color.lightGray);
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(15);
         TablaPrestamos.addCell(celda);
 
         celda = new PdfPCell(new Phrase("Valor inicial", fuenteTituloColumnas));
         celda.setBackgroundColor(Color.lightGray);
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(15);
         TablaPrestamos.addCell(celda);
 
         celda = new PdfPCell(new Phrase("Tasa de Préstamos", fuenteTituloColumnas));
         celda.setBackgroundColor(Color.lightGray);
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(15);
         TablaPrestamos.addCell(celda);
 
         celda = new PdfPCell(new Phrase("Periodo de Coutas", fuenteTituloColumnas));
         celda.setBackgroundColor(Color.lightGray);
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(15);
         TablaPrestamos.addCell(celda);
 
         celda = new PdfPCell(new Phrase("Número de Cuotas", fuenteTituloColumnas));
         celda.setBackgroundColor(Color.lightGray);
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(15);
         TablaPrestamos.addCell(celda);

         celda = new PdfPCell(new Phrase("Estado", fuenteTituloColumnas));
         celda.setBackgroundColor(Color.lightGray);
         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
         celda.setVerticalAlignment(Element.ALIGN_CENTER);
         celda.setPadding(15);
         TablaPrestamos.addCell(celda);
 
         prestamo.forEach(compr -> {
             TablaPrestamos.addCell(compr.getIdPrestamo().toString());
             TablaPrestamos.addCell(compr.getDocumentoPrestamista());
             TablaPrestamos.addCell(compr.getNombrePrestamista());
             String nombre = compr.getUsuario().getNombresUsuario() + " " + compr.getUsuario().getApellidosUsuario();
             TablaPrestamos.addCell(nombre);
             TablaPrestamos.addCell(compr.getFechaPagoOportuno());
             TablaPrestamos.addCell(compr.getValorPrestamo().toString());
             TablaPrestamos.addCell(compr.getValorPrestamoInicial().toString());
             TablaPrestamos.addCell(compr.getTasaPrestamo().toString());
             TablaPrestamos.addCell(compr.getPeriodoCuota());
             TablaPrestamos.addCell(compr.getNumeroCuotas().toString());
             TablaPrestamos.addCell(compr.getEstadoPrestamo());

         });
 
         // LLAMAR TITULO
         document.add(TablaTitulo);
 
         /* LLAMAR CAMPOS */
         document.add(TablaPrestamos);
    }
    
   
}
