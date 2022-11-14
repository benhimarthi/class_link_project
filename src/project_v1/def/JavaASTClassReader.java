package project_v1.def;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.ASTVisitor;

public class JavaASTClassReader {
	
	public static void main(String[] args) throws IOException {
		
		String filePath = "ClassTest.java";
		
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		
		char[] fileContent = FileReaders.getFileContent(filePath).toCharArray();
		
		parser.setSource(fileContent);
		
		CompilationUnit cu = (CompilationUnit)parser.createAST(null);
		
		ASTVisitor visitor = new  ASTVisitor() {
			
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName();
				int lineNumber = cu.getLineNumber(name.getStartPosition());
				
				System.out.println("Name: "+name.toString());
				System.out.println("Line: "+lineNumber);
				System.out.println("--------------------------------------------");
				return false;
			}
		};
		
		cu.accept(visitor);
	}
}
