package com.cuentas.proyectocuentas.util;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.cuentas.proyectocuentas.model.PagoPrestamo;
import com.cuentas.proyectocuentas.model.Prestamo;
import com.cuentas.proyectocuentas.model.PrestamoAbono;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Image;
@Component("views/prestamo/prestamo")
public class ListarPrestamoPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {



        // Fecha diaria automatica
        LocalDate time = LocalDate.now();

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
                }else if(user == 0 && estado == 1){
                    if (iterar.getEstadoPrestamo().equalsIgnoreCase("Activo")) {
                        prestamo.add(iterar);
                    }
                }else if(user == 0 && estado == 2){
                    if (iterar.getEstadoPrestamo().equalsIgnoreCase("Inactivo")) {
                        prestamo.add(iterar);
                    }
                }else if(user != 0){
                    if(iterar.getUsuario().getIdUsuario()== user && estado == 0){
                        prestamo.add(iterar);
                    }else if(iterar.getUsuario().getIdUsuario()== user && estado == 1){
                        if(iterar.getEstadoPrestamo().equalsIgnoreCase("Activo")){
                            prestamo.add(iterar);
                        }
                    }else if(iterar.getUsuario().getIdUsuario() == user && estado == 2){
                        if(iterar.getEstadoPrestamo().equalsIgnoreCase("Inactivo")){
                            prestamo.add(iterar);
                        }
                    }
                }
            }

            //PAGOS
            @SuppressWarnings("unchecked")
            List<PagoPrestamo> pagos = (List<PagoPrestamo>) model.get("pagos");
            List<PagoPrestamo> pago = new ArrayList<PagoPrestamo>();
            Iterator<PagoPrestamo> p = pagos.iterator();

            while (p.hasNext()) {
            
                PagoPrestamo iterar = p.next();
                if (user == 0 && estado == 0) {
                    pago.add(iterar);
                }else if(user == 0 && estado == 1){
                    if (iterar.getPrestamo().getEstadoPrestamo().equalsIgnoreCase("Activo")) {
                        pago.add(iterar);
                    }
                }else if(user == 0 && estado == 2){
                    if (iterar.getPrestamo().getEstadoPrestamo().equalsIgnoreCase("Inactivo")) {
                        pago.add(iterar);
                    }
                }else if(user != 0){
                    if(iterar.getPrestamo().getUsuario().getIdUsuario()== user && estado == 0){
                        pago.add(iterar);
                    }else if(iterar.getPrestamo().getUsuario().getIdUsuario()== user && estado == 1){
                        if(iterar.getPrestamo().getEstadoPrestamo().equalsIgnoreCase("Activo")){
                            pago.add(iterar);
                        }
                    }else if(iterar.getPrestamo().getUsuario().getIdUsuario() == user && estado == 2){
                        if(iterar.getPrestamo().getEstadoPrestamo().equalsIgnoreCase("Inactivo")){
                            pago.add(iterar);
                        }
                    }
                }
            }

            //ABONOS
            @SuppressWarnings("unchecked")
            List<PrestamoAbono> abonos = (List<PrestamoAbono>) model.get("abonos");
            List<PrestamoAbono> abono = new ArrayList<PrestamoAbono>();
            Iterator<PrestamoAbono> a = abonos.iterator();

            while (a.hasNext()) {
                // PrestamoAbono iterar = a.next();
                // abono.add(iterar);

                PrestamoAbono iterar = a.next();
                if (user == 0 && estado == 0) {
                    abono.add(iterar);
                }else if(user == 0 && estado == 1){
                    if (iterar.getPrestamo().getEstadoPrestamo().equalsIgnoreCase("Activo")) {
                        abono.add(iterar);
                    }
                }else if(user == 0 && estado == 2){
                    if (iterar.getPrestamo().getEstadoPrestamo().equalsIgnoreCase("Inactivo")) {
                        abono.add(iterar);
                    }
                }else if(user != 0){
                    if(iterar.getPrestamo().getUsuario().getIdUsuario()== user && estado == 0){
                        abono.add(iterar);
                    }else if(iterar.getPrestamo().getUsuario().getIdUsuario()== user && estado == 1){
                        if(iterar.getPrestamo().getEstadoPrestamo().equalsIgnoreCase("Activo")){
                            abono.add(iterar);
                        }
                    }else if(iterar.getPrestamo().getUsuario().getIdUsuario() == user && estado == 2){
                        if(iterar.getPrestamo().getEstadoPrestamo().equalsIgnoreCase("Inactivo")){
                            abono.add(iterar);
                        }
                    }
                }
            }

            // FUENTES
            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.WHITE);
            Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.black);

            //IMAGEN: LOGO
            Image logo = Image.getInstance("https://lh3.googleusercontent.com/fife/AAbDypBM4AdBwzYAeFejRsYGVfjZYVdfsJJCEX1YSrhPvUS4BRvAqE4hGS_su1IU2C9Zk-Q7ODLn3785a1sUplwseDNvctGR_1rS77W85lU_Lbaalxy1tu9enaeU-gmNYtxPKWcfkg-ZsfHUD3k1qWSmTip1ZRoLUGjCXfCCIyY5KuexWG8DzZ3T0pk-Mef6DOopHPW_gLKPZaAtM6vurUZWuQD-RPZYTwDbFWBBc7QenWAIuaOao68kxapry4w8wwPQ3Hcfb_7L6uJmAfn47b6Qqk8PV_DqIRSPvhT4afzZ04txKsMCEerRTfOW1SgZTcNn7TAqXWDNaNIf7pajUswDKwT2XGq5TGVAD6IE02OsNWQHY6JyzZnYp4s2f7F0dHDqQ9aIsYZdw9T91ARe2OXg4NAAgNpCnrCO_tz6IIv1SU_6WwJKwBLKt-ukZJQpjdIdVRSOWENbZIiS-k5vBG6F6LfFQi3uTimcNPgo0fK8E91zGeWid8v5bmJtx2fvXG3S-BmkYB9xbNRugkNE0dOUJmIjpR5jdE1Kc66FIc848pEcHWG9fDUkMasPl44VW7u4GcdktnWoZCBCe1Ho4dTtxhk8kNLizISho3XTrgQ9zg7Mgwsatv4SnEg6yoUMGO8p6dx9pr24NW1nnSJWQcRXFCkJTFLySIeLb26DWVloVa8hfhXaSMIrqnwecsYoAPzjBLFgoGe0i8TfOEpdqYhiQMli267c0TRnn1GMhEGMVNu-bfjcRlB_KhJGGDI5Ga_SOR79sRQ2PCaSM2KRSoshRcXYXM0ExfuRcnoE_FYlhAgMESpsNS8sJjr3zrCj5RDPKBT1AOK52rLdXCLtJJ2eY_QTzNUzamLVXt9K5ju5j0C7F1dyoqqmFs1SLBTx2jEsT7QM_0M-VhIpKFRAEEXAbwNpqizCh1H3S_XuMibnHkENkSIOpTGVALhCgr50Ds03u3n6NZ85ltPIHZ4XG1B6806sykolPJOJz4Fwy71xlt_Rq7M9IrvItk4q1F37XKh6qbSPOv4=w512");
            logo.setAlignment(Image.ALIGN_LEFT);
            logo.scaleToFit(200, 100);

            


            // MARGENES TABLA
            document.setMargins(1, 1, 20, 20);
            document.open();
            PdfPCell celda = null;
            PdfPCell celda2 = null;
            PdfPCell celdap = null;
            PdfPCell celdaa = null;

            //FECHA
            PdfPTable TablaFecha = new PdfPTable(1);
            TablaFecha.setWidths(new float[] { 0.1f});
            TablaFecha.setSpacingAfter(10);
            TablaFecha.setTotalWidth(12);
            // TablaFecha.setHorizontalAlignment(Element.ALIGN_LEFT);
            // TablaFecha.setHorizontalAlignment(Element.ALIGN_RIGHT);

            // TablaFecha.setBackgroundColor(new Color(7, 183, 18));
            TablaFecha.setWidthPercentage(20);

            TablaFecha.addCell("Fecha: "+time.toString());
            // TITULO TABLA
            PdfPTable TablaTitulo = new PdfPTable(1);

            celda = new PdfPCell(new Phrase("REPORTE PRESTAMOS", fuenteTitulo));
            celda.setBorder(0);
            celda.setBackgroundColor(new Color(7, 183, 18));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_CENTER);
            celda.setPaddingLeft(40);
            celda.setPaddingRight(40);
            celda.setPadding(30);
            

            TablaTitulo.addCell(celda);
            TablaTitulo.setSpacingAfter(15);
            TablaTitulo.setSpacingBefore(10);

            // TITULO TABLA PRÉSTAMOS
            PdfPTable TablaPrestamos = new PdfPTable(5);
            TablaPrestamos.setWidths(new float[] { 0.2f, 0.7f, 1f, 1f, 1f });
            TablaPrestamos.setSpacingAfter(10);

            celda = new PdfPCell(new Phrase("Id", fuenteTituloColumnas));
            celda.setBackgroundColor(Color.lightGray);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(1);
            TablaPrestamos.addCell(celda);

            celda = new PdfPCell(new Phrase("Valor Inicial", fuenteTituloColumnas));
            celda.setBackgroundColor(Color.lightGray);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(1);
            TablaPrestamos.addCell(celda);

            celda = new PdfPCell(new Phrase("Nombre Prestamista", fuenteTituloColumnas));
            celda.setBackgroundColor(Color.lightGray);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(1);
            TablaPrestamos.addCell(celda);

            celda = new PdfPCell(new Phrase("Fecha de Pago Oportuno", fuenteTituloColumnas));
            celda.setBackgroundColor(Color.lightGray);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(1);
            TablaPrestamos.addCell(celda);

            celda = new PdfPCell(new Phrase("Documento Prestamista", fuenteTituloColumnas));
            celda.setBackgroundColor(Color.lightGray);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(1);
            TablaPrestamos.addCell(celda);

            PdfPTable TablaPrestamos2 = new PdfPTable(6);
            TablaPrestamos2.setWidths(new float[] { 1f, 1f, 1f, 1f, 1f, 1f });
            TablaPrestamos2.setSpacingAfter(25);

            celda2 = new PdfPCell(new Phrase("Nombre Usuario", fuenteTituloColumnas));
            celda2.setBackgroundColor(Color.lightGray);
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda2.setVerticalAlignment(Element.ALIGN_CENTER);
            celda2.setPadding(1);
            TablaPrestamos2.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Valor del Préstamo", fuenteTituloColumnas));
            celda2.setBackgroundColor(Color.lightGray);
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda2.setVerticalAlignment(Element.ALIGN_CENTER);
            celda2.setPadding(1);
            TablaPrestamos2.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Tasa de Préstamos", fuenteTituloColumnas));
            celda2.setBackgroundColor(Color.lightGray);
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda2.setVerticalAlignment(Element.ALIGN_CENTER);
            celda2.setPadding(1);
            TablaPrestamos2.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Periodo de Coutas", fuenteTituloColumnas));
            celda2.setBackgroundColor(Color.lightGray);
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda2.setVerticalAlignment(Element.ALIGN_CENTER);
            celda2.setPadding(1);
            TablaPrestamos2.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Número de Cuotas", fuenteTituloColumnas));
            celda2.setBackgroundColor(Color.lightGray);
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda2.setVerticalAlignment(Element.ALIGN_CENTER);
            celda2.setPadding(1);
            TablaPrestamos2.addCell(celda2);

            celda2 = new PdfPCell(new Phrase("Estado", fuenteTituloColumnas));
            celda2.setBackgroundColor(Color.lightGray);
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda2.setVerticalAlignment(Element.ALIGN_CENTER);
            celda2.setPadding(1);
            TablaPrestamos2.addCell(celda2);

            prestamo.forEach(pr -> {
                TablaPrestamos.addCell(pr.getIdPrestamo().toString());
                TablaPrestamos.addCell(pr.getValorPrestamoInicial().toString());
                TablaPrestamos.addCell(pr.getNombrePrestamista());
                TablaPrestamos.addCell(pr.getFechaPagoOportuno());
                TablaPrestamos.addCell(pr.getDocumentoPrestamista());



                String nombre = pr.getUsuario().getNombresUsuario() + " " + pr.getUsuario().getApellidosUsuario();
                TablaPrestamos2.addCell(nombre);
                TablaPrestamos2.addCell(pr.getValorPrestamo().toString());
                TablaPrestamos2.addCell(pr.getTasaPrestamo().toString()+"%");
                TablaPrestamos2.addCell(pr.getPeriodoCuota());
                TablaPrestamos2.addCell(pr.getNumeroCuotas().toString());
                TablaPrestamos2.addCell(pr.getEstadoPrestamo());

            });

            // TITULO TABLA PAGOS
            PdfPTable TablaTituloPago = new PdfPTable(1);

            celdap = new PdfPCell(new Phrase("PAGOS", fuenteTitulo));
            celdap.setBorder(0);
            celdap.setBackgroundColor(new Color(7, 183, 18));
            celdap.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdap.setVerticalAlignment(Element.ALIGN_CENTER);
            celdap.setPadding(7);

            TablaTituloPago.addCell(celdap);
            TablaTituloPago.setSpacingAfter(10);

            //TABLA PAGOS
            PdfPTable TablaPagos = new PdfPTable(5);
            TablaPagos.setWidths(new float[] { 0.3f, 1.2f, 1f, 0.8f, 3.9f });
            TablaPagos.setSpacingAfter(25);

            celdap = new PdfPCell(new Phrase("Id", fuenteTituloColumnas));
            celdap.setBackgroundColor(Color.lightGray);
            celdap.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdap.setVerticalAlignment(Element.ALIGN_CENTER);
            celdap.setPadding(1);
            TablaPagos.addCell(celdap);

            celdap = new PdfPCell(new Phrase("Nombre Prestamista", fuenteTituloColumnas));
            celdap.setBackgroundColor(Color.lightGray);
            celdap.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdap.setVerticalAlignment(Element.ALIGN_CENTER);
            celdap.setPadding(1);
            TablaPagos.addCell(celdap);

            celdap = new PdfPCell(new Phrase("Fecha Pago", fuenteTituloColumnas));
            celdap.setBackgroundColor(Color.lightGray);
            celdap.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdap.setVerticalAlignment(Element.ALIGN_CENTER);
            celdap.setPadding(1);
            TablaPagos.addCell(celdap);

            celdap = new PdfPCell(new Phrase("Total Pago", fuenteTituloColumnas));
            celdap.setBackgroundColor(Color.lightGray);
            celdap.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdap.setVerticalAlignment(Element.ALIGN_CENTER);
            celdap.setPadding(1);
            TablaPagos.addCell(celdap);

            celdap = new PdfPCell(new Phrase("Imagen", fuenteTituloColumnas));
            celdap.setBackgroundColor(Color.lightGray);
            celdap.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdap.setVerticalAlignment(Element.ALIGN_CENTER);
            celdap.setPadding(1);
            TablaPagos.addCell(celdap);

            pago.forEach(pp -> {
                TablaPagos.addCell(pp.getIdPagoPrestamo().toString());
                TablaPagos.addCell(pp.getPrestamo().getNombrePrestamista());
                TablaPagos.addCell(pp.getFechaPago());
                TablaPagos.addCell(pp.getTotalPago().toString());

                Image image;
                try {
                    image = Image.getInstance("src//main//resources//static//assets/pimg/"+pp.getImagenPago());
                    image.setAlignment(Element.ALIGN_LEFT);
                    image.setAlignment(Element.ALIGN_LEFT);
                    // image.scaleAbsoluteHeight(100);
                    //image.scaleAbsoluteWidth(300);
                    image.scaleToFit(50f, 50f);

                    TablaPagos.addCell(image);
                } catch (BadElementException | IOException e) {
                    e.printStackTrace();
                    TablaPagos.addCell("No se detecto una imagen");

                }
                // TablaPagos.addCell(pp.getImagenPago());
            });


            // TITULO TABLA PAGOS
            PdfPTable TablaTituloAbono = new PdfPTable(1);

            celdaa = new PdfPCell(new Phrase("ABONOS", fuenteTitulo));
            celdaa.setBorder(0);
            celdaa.setBackgroundColor(new Color(7, 183, 18));
            celdaa.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaa.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaa.setPadding(7);

            TablaTituloAbono.addCell(celdaa);
            TablaTituloAbono.setSpacingAfter(10);

            //TABLA ABONOS

            // TITULO TABLA
            PdfPTable TablaAbonos = new PdfPTable(5);
            TablaAbonos.setWidths(new float[] { 0.3f, 1f, 1f, 1f, 4f });
            TablaAbonos.setSpacingAfter(10);

            celdaa = new PdfPCell(new Phrase("Id", fuenteTituloColumnas));
            celdaa.setBackgroundColor(Color.lightGray);
            celdaa.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaa.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaa.setPadding(1);
            TablaAbonos.addCell(celdaa);

            celdaa = new PdfPCell(new Phrase("Nombre Prestamista", fuenteTituloColumnas));
            celdaa.setBackgroundColor(Color.lightGray);
            celdaa.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaa.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaa.setPadding(1);
            TablaAbonos.addCell(celdaa);

            celdaa = new PdfPCell(new Phrase("Fecha Abono", fuenteTituloColumnas));
            celdaa.setBackgroundColor(Color.lightGray);
            celdaa.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaa.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaa.setPadding(1);
            TablaAbonos.addCell(celdaa);

            celdaa = new PdfPCell(new Phrase("Total Abono", fuenteTituloColumnas));
            celdaa.setBackgroundColor(Color.lightGray);
            celdaa.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaa.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaa.setPadding(1);
            TablaAbonos.addCell(celdaa);

            celdaa = new PdfPCell(new Phrase("Imagen", fuenteTituloColumnas));
            celdaa.setBackgroundColor(Color.lightGray);
            celdaa.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaa.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaa.setPadding(1);
            TablaAbonos.addCell(celdaa);
            //IMAGEN: LOGO
            // Image image = Image.getInstance("C://Users/Cristhian/Pictures/fondo-pantalla.jpg");
            // image.setAlignment(Element.ALIGN_LEFT);
            // image.setAlignment(Element.ALIGN_LEFT);
            // // image.scaleAbsoluteHeight(100);
            // //image.scaleAbsoluteWidth(300);
            // image.scaleToFit(200, 100);
            abono.forEach(ab -> {

                TablaAbonos.addCell(ab.getIdPrestamoAbono().toString());
                TablaAbonos.addCell(ab.getPrestamo().getNombrePrestamista());
                TablaAbonos.addCell(ab.getFechaAbono());
                TablaAbonos.addCell(ab.getTotalAbono().toString());

                Image image;
                try {
                    image = Image.getInstance("src//main//resources//static//assets/aimg/"+ab.getImagenAbono());
                    image.setAlignment(Element.ALIGN_LEFT);
                    image.setAlignment(Element.ALIGN_LEFT);
                    // image.scaleAbsoluteHeight(100);
                    //image.scaleAbsoluteWidth(300);
                    image.scaleToFit(200, 100);

                    TablaAbonos.addCell(image);
                } catch (BadElementException | IOException e) {
                    e.printStackTrace();
                    TablaAbonos.addCell("No de detecto una imagen");

                }


                // TablaAbonos.addCell(ab.getImagenAbono());
                // document.add(image);

            });


            //LLAMAR AL ICONO
            document.add(logo);
            document.add(TablaFecha);


            // LLAMAR TITULO
            document.add(TablaTitulo);

            //  document.add(image);

            /* LLAMAR CAMPOS */
            document.add(TablaPrestamos);
            document.add(TablaPrestamos2);


            //PAGO
            //TITULO
            document.add(TablaTituloPago);

            //TABLA
            document.add(TablaPagos);

            //ABONO
            //TITULO
            document.add(TablaTituloAbono);

            //TABLA
            document.add(TablaAbonos);

        }
    
    }

    