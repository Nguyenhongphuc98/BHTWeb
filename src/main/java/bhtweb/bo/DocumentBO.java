/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.bo;

import bhtweb.dto.ShortDocumentDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NguyenHongPhuc
 * Xử lý nghiệp vụ của document trong hệ thống
 */
public class DocumentBO {
    
    //insert
    
    //get
    
    //update
    
    public List<ShortDocumentDTO> getGoodDocs() {
        List<ShortDocumentDTO> docs = new ArrayList<>();
        docs.add(new ShortDocumentDTO("1",
                "docs1-url", "good docs bai do so 1",
                "sumaru aiig", "Nguyen Hong Phuc",
                "1", "1", "Chuyen tam linh", "1",
                "Tam ly hoc", "22/15/2030",
                "image",999, 23));
        docs.add(new ShortDocumentDTO("2",
                "docs1-url", "good docs bai do so 3",
                "sumaru aiig", "Nguyen Hong Pink",
                "1", "1", "Chuyen tam linh", "1",
                "Tam ly hoc", "22/15/2030",
                "image",999, 23));
        docs.add(new ShortDocumentDTO("3",
                "docs1-url", "good docs bai do so 3",
                "sumaru aiig", "Nguyen Hong Blue",
                "1", "1", "Chuyen tam linh", "1",
                "Tam ly hoc", "22/15/2030",
                "image",999, 23));
        return docs;
    }
    
     public List<ShortDocumentDTO> getAllDocs() {
        List<ShortDocumentDTO> docs = new ArrayList<>();
        docs.add(new ShortDocumentDTO("1",
                "docs1-url", "all docs bai do so 1",
                "sumaru aiig", "Nguyen Hong Phuc",
                "1", "1", "Chuyen tam linh", "1",
                "Tam ly hoc", "22/15/2030",
                "image",999, 23));
        docs.add(new ShortDocumentDTO("2",
                "docs1-url", "all docs bai do so 3",
                "sumaru aiig", "Nguyen Hong Pink",
                "1", "1", "Chuyen tam linh", "1",
                "Tam ly hoc", "22/15/2030",
                "image",999, 23));
        docs.add(new ShortDocumentDTO("3",
                "docs1-url", "gall docs bai do so 3",
                "sumaru aiig", "Nguyen Hong Blue",
                "1", "1", "Chuyen tam linh", "1",
                "Tam ly hoc", "22/15/2030",
                "image",999, 23));
        return docs;
    }
}
