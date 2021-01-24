
/**
 * Enumeration class Apercibimientos - write a description of the enum class here
 * 
 * @author Diego Arbeloa
 */
public enum Apercibimientos
{
    DIEZ("DIEZ",10), VEINTE("DIEZ VEINTE",20), TREINTA("DIEZ VEINTE TREINTA",30), SIN_APERCIBIMIENTOS("Sin apercibimientos",0);
    private int nFaltas;
    private String apercibimiento;
    private Apercibimientos(String aper, int nFaltas)
    {
        this.apercibimiento = aper;
        this.nFaltas = nFaltas;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public String getApercibimiento()
    {
        return apercibimiento;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public int getNFaltas()
    {
        return nFaltas;
    }

}
