package com.cuentas.proyectocuentas.util;

import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.cuentas.proyectocuentas.model.Compromiso;
import com.cuentas.proyectocuentas.model.Usuario;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
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

        Integer dato = Integer.parseInt(request.getParameter("id"));
        Integer estado = Integer.parseInt(request.getParameter("estado"));

        Usuario user = getUser();
        if (dato!=user.getIdUsuario() && !user.getTipoUsuario().equalsIgnoreCase("administrador")) {
            response.sendError(403);
        }

        @SuppressWarnings("unchecked")
        List<Compromiso> compromisos = (List<Compromiso>) model.get("compromisos");

        List<Compromiso> compromiso = new ArrayList<Compromiso>();
        Iterator<Compromiso> i = compromisos.iterator();
                
        while (i.hasNext()) {
            Compromiso iterar = i.next();
            if (dato == 0 && estado == 0) {
                compromiso.add(iterar);
            } else if (dato == 0 && estado == 2){
                if (iterar.getEstadoCom().equalsIgnoreCase("Por pagar")) {
                    compromiso.add(iterar);
                }
            } else if (dato == 0 && estado == 1){
                if (iterar.getEstadoCom().equalsIgnoreCase("pago")) {
                    compromiso.add(iterar);
                }
            } else if (dato != 0) {
                if (iterar.getUsuario().getIdUsuario() == dato && estado == 0) {
                    compromiso.add(iterar);
                }
                else if (iterar.getUsuario().getIdUsuario() == dato && estado == 2) {
                    if (iterar.getEstadoCom().equalsIgnoreCase("Por pagar")) {
                        compromiso.add(iterar);
                    }
                }
                else if (iterar.getUsuario().getIdUsuario() == dato && estado == 1) {
                    if (iterar.getEstadoCom().equalsIgnoreCase("pago")) {
                        compromiso.add(iterar);
                    }
                }
            }
        }
        // Fecha diaria automatica
        LocalDate time = LocalDate.now();
        // Hora automatica
        LocalDateTime hora = LocalDateTime.now();
        String formato="HH:mm";
        DateTimeFormatter.ofPattern(formato).format(hora).toString();
     
        // FUENTES
        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25, Color.white);
        Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.black);

        // HOJA HORIZONTAL
        document.setPageSize(PageSize.LETTER.rotate());
        document.setPageSize(PageSize.ARCH_A.rotate());

        // MARGENES TABLA
        document.setMargins(40, 10, 40, 20);
        document.open();
        PdfPCell celda = null;
        PdfPCell celdah = null;
        PdfPCell celdaf = null;

        //FECHA
        PdfPTable TablaFecha = new PdfPTable(1);
        TablaFecha.setWidths(new float[] { 10f});
        // TablaFecha.setSpacingAfter(10);
        TablaFecha.setTotalWidth(1);
        TablaFecha.setHorizontalAlignment(Element.ALIGN_RIGHT);
        // TablaFecha.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celdaf = new PdfPCell(new Phrase("Fecha: "+time.toString()));
        celdah = new PdfPCell(new Phrase("Hora: "+DateTimeFormatter.ofPattern(formato).format(hora).toString()));
        celdaf.setPadding(0);
        celdaf.setBorder(0);
        TablaFecha.addCell(celdaf);
        celdah.setPadding(0);
        celdah.setBorder(0);
        
        TablaFecha.addCell(celdah);

         // TablaFecha.setBackgroundColor(new Color(7, 183, 18));
         TablaFecha.setWidthPercentage(23);

        //  TablaFecha.addCell("Fecha: "+time.toString());



        //IMAGEN: LOGO
        Image image = Image.getInstance("./src/main/resources/static/assets/img/Logo-cuentascasa.png");
       image.setAlignment(Element.ALIGN_LEFT);
       image.setAlignment(Element.ALIGN_LEFT);
      // image.scaleAbsoluteHeight(100);
       //image.scaleAbsoluteWidth(300);
       image.scaleToFit(200, 100);
    
       


        // TITULO TABLA
        PdfPTable TablaTitulo = new PdfPTable(1);

        celda = new PdfPCell(new Phrase("REPORTE COMPROMISOS", fuenteTitulo));
        //celda.setBorder(0);
        TablaTitulo.setSpacingBefore(30);
        celda.setBackgroundColor(new Color(7, 183, 18));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(30);
       //  celda.setBorderColor(new Color(7, 183, 18));
        TablaTitulo.addCell(celda);
        TablaTitulo.setSpacingAfter(20);

        
        // TITULO TABLA
        PdfPTable TablaCompromisos = new PdfPTable(10);
        TablaCompromisos.setWidths(new float[] { 2, 5, 6, 6, 6, 4, 4, 4, 6, 5 });

        celda = new PdfPCell(new Phrase("#", fuenteTituloColumnas));
        celda.setBackgroundColor(new Color(144, 226, 100));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPaddingTop(12);
        celda.setPaddingBottom(8);
        TablaCompromisos.addCell(celda);

        celda = new PdfPCell(new Phrase("Numero Factura", fuenteTituloColumnas));
        celda.setBackgroundColor(new Color(144, 226, 100));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPaddingTop(10);
        celda.setPaddingBottom(8);
        TablaCompromisos.addCell(celda);

        celda = new PdfPCell(new Phrase("Nombre Empresa", fuenteTituloColumnas));
        celda.setBackgroundColor(new Color(144, 226, 100));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPaddingTop(10);
        celda.setPaddingBottom(8);
        TablaCompromisos.addCell(celda);

        celda = new PdfPCell(new Phrase("Fecha Pago", fuenteTituloColumnas));
        //celda.setBackgroundColor(Color.lightGray);
        celda.setBackgroundColor(new Color(144, 226, 100));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPaddingTop(10);
        celda.setPaddingBottom(8);
        TablaCompromisos.addCell(celda);

        celda = new PdfPCell(new Phrase("Fecha Suspension", fuenteTituloColumnas));
        celda.setBackgroundColor(new Color(144, 226, 100));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPaddingTop(10);
        celda.setPaddingBottom(8);
        TablaCompromisos.addCell(celda);

        celda = new PdfPCell(new Phrase("Metodo Pago", fuenteTituloColumnas));
        celda.setBackgroundColor(new Color(144, 226, 100));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPaddingTop(10);
        celda.setPaddingBottom(8);
        TablaCompromisos.addCell(celda);

        celda = new PdfPCell(new Phrase("Total", fuenteTituloColumnas));
        celda.setBackgroundColor(new Color(144, 226, 100));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPaddingTop(12);
        celda.setPaddingBottom(8);
        TablaCompromisos.addCell(celda);

        celda = new PdfPCell(new Phrase("Usuario", fuenteTituloColumnas));
        celda.setBackgroundColor(new Color(144, 226, 100));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPaddingTop(12);
        celda.setPaddingBottom(8);
        TablaCompromisos.addCell(celda);

        celda = new PdfPCell(new Phrase("Tipo Compromiso", fuenteTituloColumnas));
        celda.setBackgroundColor(new Color(144, 226, 100));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPaddingTop(10);
        celda.setPaddingBottom(8);
        TablaCompromisos.addCell(celda);

        celda = new PdfPCell(new Phrase("Estado", fuenteTituloColumnas));
        celda.setBackgroundColor(new Color(144, 226, 100));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPaddingTop(12);
        celda.setPaddingBottom(8);
        TablaCompromisos.addCell(celda);

        compromiso.forEach(compr -> {
            TablaCompromisos.addCell(compr.getIdCom().toString());
            TablaCompromisos.addCell(compr.getNumeroFac());
            TablaCompromisos.addCell(compr.getNombreEm());
            TablaCompromisos.addCell(compr.getFecha());
            TablaCompromisos.addCell(compr.getFechaS());
            TablaCompromisos.addCell(compr.getMetodo());
            TablaCompromisos.addCell(compr.getTotal());
            String nombre = compr.getUsuario().getNombresUsuario() + " " + compr.getUsuario().getApellidosUsuario();
            TablaCompromisos.addCell(nombre);
            TablaCompromisos.addCell(compr.getTipocompromiso().getNombre());
            TablaCompromisos.addCell(compr.getEstadoCom());
        });

      
       
       
       


        document.add(image);
        document.add(TablaFecha);
    
        // LLAMAR TITULO
        document.add(TablaTitulo);

        /* LLAMAR CAMPOS */
        document.add(TablaCompromisos);

        
    }

    public final Usuario getUser() {

        RequestAttributes requestAttributes = RequestContextHolder
                .currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        HttpSession httpSession = request.getSession(true);
    
        Object userObject = httpSession.getAttribute("usuario");
        if (userObject == null) {
            return null;
        }
    
        Usuario user = (Usuario) userObject;

        return user;
    }

}
