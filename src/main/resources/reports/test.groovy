import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun

XWPFDocument doc = new XWPFDocument()
XWPFParagraph p1 = doc.createParagraph()
XWPFRun r1 = p1.createRun()
r1.setBold(true)
r1.setText("The quick brown fox")
XWPFParagraph p2 = doc.createParagraph()
XWPFRun r2 = p2.createRun()
r2.setText("jumped over the lazy dog")
XWPFRun r3 = p2.createRun()
r3.setText("and went away")
r3.setStrikeThrough(true)

vars['document'] = doc
