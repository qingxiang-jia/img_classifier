package view;

public class TagGen
{
    String path;

    public String writeImg(String filename)
    {
        return "<img src=\"" + path + "/" + filename + "\">";
    }

    public String writeP(String content)
    {
        return "<p>" + content + "</p>";
    }

    public String writeDiv1(String id)
    {
        return "<div id=\"" + id + "\">";
    }

    public String writeDiv2()
    {
        return "</div>";
    }

    public String writeHeader()
    {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n";
    }

    public String writeTail()
    {
        return "</body>\n" +
                "</html>";
    }

    public void setPath(String path)
    {
        this.path = path;
    }
}
