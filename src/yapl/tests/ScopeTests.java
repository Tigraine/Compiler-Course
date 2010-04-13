package yapl.tests;



import yapl.Scope;
import yapl.SymbolImpl;
import yapl.SymboltableImpl;
import yapl.interfaces.Symbol;
import yapl.interfaces.Symbol.SymbolKind;
import yapl.lib.YAPLException;
import junit.framework.Assert;
import junit.framework.TestCase;

public class ScopeTests extends TestCase {
	
	public void testAddSymbol(){
		Scope scope = new Scope();
		try {
			scope.addSymbol(new SymbolImpl("a", Symbol.SymbolKind.Variable));
		} catch (YAPLException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	public void testGetSymbol() throws YAPLException {
		Scope scope = new Scope();
		SymbolImpl symbol = getSampleSymbol();
		scope.addSymbol(symbol);
		
		Symbol returnSymbol = scope.getSymbol("a");
		
		Assert.assertEquals(returnSymbol, symbol);
	}
	
	public void testAddSymbolShouldFailWhenSymbolAlreadyDefinedInScope() throws YAPLException {
		Scope scope = new Scope();
		SymbolImpl symbol1 = getSampleSymbol();
		SymbolImpl symbol2 = getSampleSymbol();
		scope.addSymbol(symbol1);
		try {
			scope.addSymbol(symbol2);
		} catch (YAPLException ex) {
			
		}
	}
	
	public void testRecursivelySearchesSymbols() throws YAPLException {
		Scope scope1 = new Scope();
		Scope scope2 = new Scope(scope1);
		SymbolImpl symbol1 = getSampleSymbol();
		scope1.addSymbol(symbol1);
		Symbol symbol = scope2.getSymbol("a"); //Retrieve from child scope
		Assert.assertEquals(symbol1, symbol);
	}
	
	public void testThrowsIfSymbolnameNotFound() {
		Scope scope = new Scope();	
		try {
			scope.getSymbol("a");
			Assert.fail();
		} catch (YAPLException e) {
		}
	}
	
	public void testAlwaysResolvesToNearestScope() throws YAPLException {
		Scope scope1 = new Scope();
		Scope scope2 = new Scope(scope1);
		SymbolImpl symbol1 = getSampleSymbol();
		SymbolImpl symbol2 = getSampleSymbol();
		
		scope1.addSymbol(symbol1);
		scope2.addSymbol(symbol2);
		
		Symbol symbol = scope2.getSymbol("a");
		Assert.assertEquals(symbol2, symbol);
	}
	
	public void testSetsGlobalOnSymbolIfAddedToHightestScope() throws YAPLException {
		Scope scope = new Scope();
		SymbolImpl symbol = getSampleSymbol();
		
		scope.addSymbol(symbol);
		
		Assert.assertEquals(true, symbol.isGlobal());
	}
	
	public void testSetsGlobalOffForSymbolsAddedToLowerScopes() throws YAPLException {
		Scope scope = new Scope();
		Scope scope2 = new Scope(scope);
		SymbolImpl symbol = getSampleSymbol();
		
		scope2.addSymbol(symbol);
		
		Assert.assertEquals(false, symbol.isGlobal());
	}
	
	public void testSetCurrentSymbol() {
		Scope scope = new Scope();
		SymbolImpl symbol = getSampleSymbol();
	
		scope.setParentSymbol(symbol);
		
		Symbol nearestParentSymbol = scope.getNearestParentSymbol(SymbolKind.Variable);
		
		Assert.assertEquals(symbol, nearestParentSymbol);
	}

	private SymbolImpl getSampleSymbol() {
		SymbolImpl symbol = new SymbolImpl("a", Symbol.SymbolKind.Variable);
		return symbol;
	}
	
	public void testGetCurrentSymbolRecursion() {
		Scope scope = new Scope();
		Scope scope2 = new Scope(scope);
		
		SymbolImpl symbol = getSampleSymbol();
		scope.setParentSymbol(symbol);
		
		Symbol nearestParentSymbol = scope2.getNearestParentSymbol(SymbolKind.Variable);
		
		Assert.assertEquals(symbol, nearestParentSymbol);
	}
}
