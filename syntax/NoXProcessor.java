package syntax;

import java.util.Set;
import javax.lang.model.*;
import javax.lang.model.element.*;
import javax.lang.model.util.*;
import javax.annotation.processing.*;
import com.sun.source.util.*;
import com.sun.source.tree.*;
import javax.tools.Diagnostic.Kind;


@SupportedAnnotationTypes({"syntax.NoX"})
public class NoXProcessor extends AbstractProcessor {
    private Trees trees;
    private CodeAnalyzerTreeVisitor visitor;

    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }
    
    private class CodeAnalyzerTreeVisitor extends TreePathScanner<Object, Trees>  {
        ProcessingEnvironment pe;
        public CodeAnalyzerTreeVisitor(ProcessingEnvironment pe) {
            super();
            this.pe = pe;
        }
        @Override
        public Object visitVariable(VariableTree decl, Trees trees) {
            System.out.printf("Variable: %s\n", decl.toString());
            if (decl.getName().toString().contains("x")) {
                pe.getMessager().printMessage(Kind.ERROR, "variable " + decl.getName().toString() + " contains the letter \"x\"", trees.getElement(this.getCurrentPath()));
            }
            return null;
        }

        @Override
        public Object visitAssignment(AssignmentTree assn, Trees trees) {
            System.out.printf("Assignment: %s\n", assn.toString());
            return null;
        }
            
        @Override
        public Object visitCompoundAssignment(CompoundAssignmentTree assn, Trees trees) {
            System.out.printf("CompoundAssignment: %s\n", assn.toString());
            return null;
        }
    }

    @Override
    public void init(ProcessingEnvironment pe) {
        super.init(pe);
        this.trees = Trees.instance(pe);
        this.visitor = new CodeAnalyzerTreeVisitor(pe);
    }

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        for (Element e : env.getRootElements()) {
            TreePath tp = trees.getPath(e);
            visitor.scan(tp, trees);
        }
        return false;
    }
}
