package display;

//import java.awt.Dimension;
//import java.awt.FlowLayout;

import javax.swing.Box;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
//import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.ObsProperty;

import static org.semanticweb.owlapi.vocab.OWLFacet.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.coode.owlapi.turtle.TurtleOntologyFormat;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.io.SystemOutDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLDataUnionOf;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDatatypeDefinitionAxiom;
import org.semanticweb.owlapi.model.OWLDatatypeRestriction;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLFacetRestriction;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLObjectPropertyAtom;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.semanticweb.owlapi.reasoner.BufferingMode;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.semanticweb.owlapi.util.OWLOntologyWalker;
import org.semanticweb.owlapi.util.OWLOntologyWalkerVisitor;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLFacet;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import uk.ac.manchester.cs.owlapi.modularity.ModuleType;
import uk.ac.manchester.cs.owlapi.modularity.SyntacticLocalityModuleExtractor;

public class GUIConsole extends Artifact {

    private Display display;

    void init(String name) {
        // creates an observable property called numMsg
        this.defineObsProperty("numMsg",0);
        display = new Display(name);
        display.setVisible(true);
        try {
			shouldLoad();
		} catch (OWLOntologyCreationException e) {
			System.out.println("Expection");
		}
    }
    
    // implements an operation available to the agents
    @OPERATION void printMsg(String msg) {	
        String agentName = this.getOpUserName();
        ObsProperty prop = this.getObsProperty("numMsg");
        prop.updateValue(prop.intValue()+1);
        display.addText("Message at "+System.currentTimeMillis()+" from "+agentName+": "+msg);
        display.updateNumMsgField(prop.intValue());
        signal("tick");
    }    
    private static final String PIZZA_IRI = "http://www.co-ode.org/ontologies/pizza/pizza.owl";
    
    public void shouldLoad() throws OWLOntologyCreationException {
        // Get hold of an ontology manager
    		System.out.println("Hey Trying to fetch the ontology");
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        // Let's load an ontology from the web
        IRI iri = IRI.create("https://protege.stanford.edu/ontologies/pizza/pizza.owl");
        OWLOntology pizzaOntology = manager.loadOntologyFromOntologyDocument(iri);
        System.out.println("Loaded ontology: " + pizzaOntology);
//        display.addText("Loaded ontology: " + pizzaOntology);
        
//        IRI documentIRI = manager.getOntologyDocumentIRI(iri);
//      System.out.println("\nfrom: " + documentIRI);
      
      System.out.println("\nClasses\n");
      int c=0;
      for (OWLClass cls : pizzaOntology.getClassesInSignature()) {
          String id = cls.getIRI().toString();
//          System.out.println(++c + " : " + id + "\n");
          String[] str_array = id.split("#");
//          String stringa = str_array[0]; 
          String stringb = str_array[1];
          System.out.println(++c + " : " + stringb);
      }
      
      OWLDataFactory factory = manager.getOWLDataFactory();
      PrefixManager pm = new DefaultPrefixManager(
              "http://www.co-ode.org/ontologies/pizza/pizza.ow#");
      OWLClass clsAMethodB = factory.getOWLClass(":American", pm);
      System.out.println(clsAMethodB);
      
//      OWLNamedIndividual mary = factory.getOWLNamedIndividual(":Mary", pm);
      
      OWLDeclarationAxiom declarationAxiom = factory
              .getOWLDeclarationAxiom(clsAMethodB);
      System.out.println(declarationAxiom);
      
//      java.util.Set<OWLEntity> entOnt = pizzaOntology.getSignature();
//
//      for (OWLEntity a : entOnt) {
//      System.out.println("\nEntity " + a);
//      }
        
    }
    
    static class Display extends JFrame {       
        
        private JTextArea text;
        private JLabel numMsg;
        private static int n = 0;
        
        public Display(String name) {
            setTitle(".:: "+name+" console ::.");
            
            JPanel panel = new JPanel();
            setContentPane(panel);
            panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
            
            numMsg = new JLabel("0");
            text = new JTextArea(15,40);

            panel.add(text);
            panel.add(Box.createVerticalStrut(5));
            panel.add(numMsg);
            pack();
            setLocation(n*40, n*80);
            setVisible(true);
            
            n++;
        }
        
        public void addText(final String s){
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {
                    text.append(s+"\n");
                }
            });
        }
        
        public void updateNumMsgField(final int value){
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {
                    numMsg.setText(""+value);
                }
            });
        }
    }
}