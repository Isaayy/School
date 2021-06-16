package com.example.demo.components;

import com.example.demo.dto.QuestionDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a PDF file create component
 */
@Component
public class DocumentComponent {

    private Boolean didUserPass;

    public Boolean getDidUserPass() {
        return didUserPass;
    }

    public DocumentComponent() {
        this.didUserPass = false;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentComponent.class);

    /**
     * This method create PDF document
     * @param fileDestination Path for our results PDF file
     * @param userPoints Amount of points obtained by the user
     * @param minPoints The minimum number of points needed to pass the test
     * @param maxPoints The maximum number of points
     * @param questions List of questions
     * @param userAnswers User answers
     * @param isCorrect Resulting list - yes / no answers
     */
    public void createDocument(String fileDestination, int userPoints, int minPoints , int maxPoints , List<QuestionDto> questions, List<List<String>> userAnswers , List<String> isCorrect) {
        try {

            final String FONT = "static/arial.ttf";
            Font headingFont = FontFactory.getFont(FONT, 25,  BaseColor.BLACK);
            Font fontBlack = FontFactory.getFont(FONT, 16,  BaseColor.BLACK);
            Font fontRed = FontFactory.getFont(FONT, 16,  BaseColor.RED);
            Font fontGreen = FontFactory.getFont(FONT, 16,  BaseColor.GREEN);

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileDestination));
            document.open();

            Paragraph p = new Paragraph("Here are your quiz results", headingFont);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(50f);
            document.add(p);

            if (userPoints >= minPoints) {
                Paragraph p1 = new Paragraph("Congratulations you passed the quiz.", fontGreen);
                p1.setAlignment(Element.ALIGN_CENTER);
                p1.setSpacingAfter(2f);
                document.add(p1);
                didUserPass = true;
            }
            else {
                Paragraph p1 = new Paragraph("Unfortunately you didn't pass the quiz.", fontRed);
                p1.setAlignment(Element.ALIGN_CENTER);
                p1.setSpacingAfter(2f);
                document.add(p1);
            }

            Paragraph p2 = new Paragraph("Your score is " + userPoints + "/" + maxPoints , fontBlack);
            p2.setAlignment(Element.ALIGN_CENTER);
            p2.setSpacingAfter(50f);
            document.add(p2);

            Paragraph p3 = new Paragraph("Answers: ", headingFont);
            p3.setAlignment(Element.ALIGN_CENTER);
            document.add(p3);

            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(10f);
            table.setWidths(new int[]{5, 5, 5 ,3});
            table.setWidthPercentage(100);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);

            // Create table header cells
            table.addCell(createHeaderCell("Question", 2, Element.ALIGN_LEFT));
            table.addCell(createHeaderCell("Correct answers", 2, Element.ALIGN_LEFT));
            table.addCell(createHeaderCell("Selected answers", 2, Element.ALIGN_LEFT));
            table.addCell(createHeaderCell("Is correct", 2, Element.ALIGN_LEFT));

            // Create table body cells
            for (int i = 1 ; i < questions.size(); i++ ){
                table.addCell(createCell(questions.get(i).getQuestion(), 1, Element.ALIGN_LEFT));

                // Create array of correct answers and add it to table cells
                List<String> correctAnswers = new ArrayList<>();
                for (int k = 0 ; k < questions.get(i).getCorrect().length ; k++)
                    correctAnswers.add(questions.get(i).getAnswers().get(questions.get(i).getCorrect()[k]-1));

                table.addCell(createCell(correctAnswers.toString().substring(1, correctAnswers.toString().length() - 1), 1, Element.ALIGN_LEFT));
                table.addCell(createCell(userAnswers.get(i-1).toString().substring(1, userAnswers.get(i-1).toString().length() - 1), 1, Element.ALIGN_LEFT));
                table.addCell(createCell(isCorrect.get(i-1), 1, Element.ALIGN_LEFT));

            }

            document.add(table);

            document.close();

        } catch (DocumentException | FileNotFoundException e) {
            LOGGER.error("I can't create document or file not exists");
        }
    }

    /**
     * This method is creator of cell in our PDF file
     * @param content Cell text content
     * @param borderWidth Width of the cell's border
     * @param alignment Cell alignment in document
     * @return Cell of PdfPCell table
     */
    private PdfPCell createCell(String content, float borderWidth, int alignment) {
        final String FONT = "static/arial.ttf";

        Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);

        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBorderWidth(borderWidth);
        cell.setHorizontalAlignment(alignment);
        cell.setPaddingTop(3f);
        cell.setPaddingBottom(6f);
        cell.setPaddingLeft(3f);
        cell.setPaddingRight(3f);
        return cell;
    }

    /**
     * This method is creator of header cell
     * @param content Cell text content
     * @param borderWidth Width of the cell's border
     * @param alignment Cell alignment in document
     * @return Cell of PdfPCell table
     */
    private PdfPCell createHeaderCell(String content, float borderWidth, int alignment) {
        final String FONT = "static/arial.ttf";

        Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);

        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBorderWidth(borderWidth);
        cell.setHorizontalAlignment(alignment);
        cell.setPaddingTop(10f);
        cell.setPaddingBottom(14f);
        cell.setPaddingLeft(10f);
        cell.setPaddingRight(10f);
        return cell;
    }

}
