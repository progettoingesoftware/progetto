package it.ing.sw.v1;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.*;

/**
 * Questa classe contiene i dati dei vari fruitori e permette il controllo e la verifica di specifiche condizioni necessarie per il mantenimento e l'aggiornamento dei dati
 */
public class AnagraficaFruitori extends Anagrafica implements Serializable
{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String INTESTAZIONE_ELENCO = "Elenco degli attuali fruitori: \n";
    public static final int DIECI_GIORNI = 10;
	
    /**
     * Metodo costruttore della classe AnagraficaFruitori
     * 
     * Post : elenco != null
     */    
    public AnagraficaFruitori()
    {
   	    super();
    }
    
    /**
     * Metodo che permette l'aggiunta di un fruitore all'elenco dei fruitori
     * 
     * Post : elenco.contains(f)
     * 
     * @param f : l'oggetto fruitore che si desidera aggiungere
     */
    public void aggiungiFruitore(Fruitore f)  
    {
   	    elenco.add(f);
    }
    
    /**
     * Metodo che verifica se il fruitore che intende iscriversi ha contemporaneamente lo stesso nome, lo stesso cognome e la stessa data di nascita di almeno uno dei fruitori già iscritti
     * 
     * Pre : elenco != null
     * 
     * @param n : nome del nuovo fruitore
     * @param c : cognome del nuovo fruitore
     * @param dn : data di nascita del nuovo fruitore
     * @return boolean : true se le condizioni di uguaglianza sono verificate
     */
    public boolean verificaOmonimiaFruitori(String n, String c, LocalDate dn)
    {
    	   for(int i = 0; i < elenco.size(); i++)
    	   {
    		   Fruitore f = (Fruitore) elenco.get(i);
    		   
    		   if((f.getNome()).equalsIgnoreCase(n) && (f.getCognome().equalsIgnoreCase(c)) && (f.getDataDiNascita().isEqual(dn)))
                 return true;
    	   }
    	   
    	   return false;
    }
    
    /**
     * Metodo che verifica se il fruitore che intende iscriversi ha lo stesso username di almeno uno dei fruitori gia' iscritti
     * 
     * Pre : elenco != null
     * 
     * @param u : username del nuovo fruitore
     * @return boolean : true se la condizione di uguaglianza e' verificata
     */
    public boolean verificaStessoUsername(String u) 
    {
   	    for(int i = 0; i < elenco.size() ; i++)
   	    {
   	       Fruitore f = (Fruitore) elenco.get(i);
   	    	   
   	    	   if((f.getUsername()).equals(u))
   	    		      return true;
   	    }
   	    
   	    return false;
    }
   
    /**
     * Metodo che verifica se la data corrente sia successiva rispetto alla data di scadenza del servizio prevista per uno specifico fruitore;
     * in tal caso procede con la rimozione del suddetto fruitore dall'elenco dei fruitori iscritti
     * 
     * Pre : elenco != null
     * Post : elenco.contains(f) == false
     */
    public void decadenzaFruitore()
    {
   	 	for(int i = 0; i < elenco.size() ; i++)
   	 	{
   	 		Fruitore f = (Fruitore) elenco.get(i);	
   	 		
   	 		if((LocalDate.now().isAfter(f.getDataDiScadenza())))
	    	 			elenco.remove(f);    	    	 			
   	 	}
	   
    }
    
    /**
     * Metodo che verifica se la data corrente sia compresa nel periodo tra i dieci giorni antecedenti la data di scadenza del servizio (prevista per uno specifico fruitore) e la stessa data di scadenza;
     * in tal caso procede con la modifica della data di scadenza del suddetto fruitore aggiornandola con la data corrente
     * 
     * Pre : elenco != null

     * Post : f.getDatadiScadenza().isEqual(LocalDate.now().plusYears(Fruitore.DATA_DI_SCADENZA))
     * 
     * @param u : username del fruitore
     * @return boolean : true se la condizione indicata e' verificata
     */  
    public boolean rinnovoIscrizioneFruitore(String u)
    {
   	 	for(int i = 0; i < elenco.size() ; i++)
   	 	{
   	 			Fruitore f = (Fruitore) elenco.get(i);
   	 			
   	 			if(f.getUsername().equals(u))
   	 			{
   	 				/**
   	 				 * La verifica della data avviene mediante due if concatenati:
   	 				 * il primo verifica che la data corrente preceda quella di scadenza indicata per lo specifico fruitore;
   	 				 * il secondo verifica che la data corrente succeda quella di scadenza (indicata per lo specifico fruitore) diminuita di un periodo di 10 giorni
   	 				 */
   	 				if((LocalDate.now().isBefore(f.getDataDiScadenza())))
   	 				{
       	 				if((LocalDate.now().isAfter(f.getDataDiScadenza().minusDays(DIECI_GIORNI))))
       	 				{
   	 						f.setDataDiScadenza(LocalDate.now().plusYears(Fruitore.TERMINE_SCADENZA));
       	 					return true;
       	 				}
   	 				}
   	 			}		
   	 	}
	    
   	 	return false;
    }  
    
    /**
     * Metodo toString() ereditato dalla classe String per la creazione di una stringa descrittiva contenente l'elenco ordinato dei vari fruitori
     * 
     * Pre : elenco != null
     * 
     * @return la stringa descrittiva
     */    
    public String toString()		
    {
   	    StringBuffer ris = new StringBuffer();
   	    ris.append(INTESTAZIONE_ELENCO);
   	    
   	    for(int i = 0; i < elenco.size(); i++)
   	    {
   	    	    Fruitore f = (Fruitore) elenco.get(i);
   	    	    ris.append(i+1 + ")" + f.toString() + "\n");
   	    }
   	    
   	    return ris.toString();
    }
        
}
