-- =========================================================================
-- Exercise 2: Error Handling
-- Scenario 1: SafeTransferFunds stored procedure with robust exception handling.
-- =========================================================================

-- Enable DBMS_OUTPUT for printing to console
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_SourceAccountID IN NUMBER,
    p_DestAccountID   IN NUMBER,
    p_Amount          IN NUMBER
) AS
    v_SourceBalance  NUMBER;
    v_SourceCount    NUMBER;
    v_DestCount      NUMBER;
    
    -- Custom Exceptions
    e_insufficient_funds EXCEPTION;
    e_invalid_account    EXCEPTION;
    e_negative_amount    EXCEPTION;
    e_same_accounts      EXCEPTION;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Attempting transfer of $' || p_Amount || ' from Account ' || p_SourceAccountID || ' to Account ' || p_DestAccountID);
    
    -- Start Transaction Savepoint
    SAVEPOINT start_transfer;

    -- 1. Validate negative or zero amount
    IF p_Amount <= 0 THEN
        RAISE e_negative_amount;
    END IF;

    -- 2. Validate distinct accounts
    IF p_SourceAccountID = p_DestAccountID THEN
        RAISE e_same_accounts;
    END IF;

    -- 3. Check if source account exists
    SELECT COUNT(*) INTO v_SourceCount FROM Accounts WHERE AccountID = p_SourceAccountID;
    IF v_SourceCount = 0 THEN
        RAISE e_invalid_account;
    END IF;

    -- 4. Check if destination account exists
    SELECT COUNT(*) INTO v_DestCount FROM Accounts WHERE AccountID = p_DestAccountID;
    IF v_DestCount = 0 THEN
        RAISE e_invalid_account;
    END IF;

    -- 5. Lock and check source balance
    SELECT Balance INTO v_SourceBalance 
    FROM Accounts 
    WHERE AccountID = p_SourceAccountID 
    FOR UPDATE;
    
    IF v_SourceBalance < p_Amount THEN
        RAISE e_insufficient_funds;
    END IF;

    -- 6. Perform the updates
    UPDATE Accounts 
    SET Balance = Balance - p_Amount, LastModified = SYSDATE 
    WHERE AccountID = p_SourceAccountID;
    
    UPDATE Accounts 
    SET Balance = Balance + p_Amount, LastModified = SYSDATE 
    WHERE AccountID = p_DestAccountID;

    -- 7. Record transactions in the Transactions table
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (Transactions_Seq.NEXTVAL, p_SourceAccountID, SYSDATE, p_Amount, 'Withdrawal');

    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (Transactions_Seq.NEXTVAL, p_DestAccountID, SYSDATE, p_Amount, 'Deposit');

    -- Commit the transaction
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('SUCCESS: Transfer completed successfully.');
    
EXCEPTION
    WHEN e_negative_amount THEN
        ROLLBACK TO start_transfer;
        DBMS_OUTPUT.PUT_LINE('ERROR: Transfer amount must be positive. Transaction rolled back.');
    WHEN e_same_accounts THEN
        ROLLBACK TO start_transfer;
        DBMS_OUTPUT.PUT_LINE('ERROR: Source and destination accounts cannot be the same. Transaction rolled back.');
    WHEN e_invalid_account THEN
        ROLLBACK TO start_transfer;
        DBMS_OUTPUT.PUT_LINE('ERROR: Source or destination account ID is invalid. Transaction rolled back.');
    WHEN e_insufficient_funds THEN
        ROLLBACK TO start_transfer;
        DBMS_OUTPUT.PUT_LINE('ERROR: Insufficient funds in Account ' || p_SourceAccountID || 
                             ' (Available: $' || v_SourceBalance || '). Transaction rolled back.');
    WHEN OTHERS THEN
        ROLLBACK TO start_transfer;
        DBMS_OUTPUT.PUT_LINE('UNEXPECTED ERROR: ' || SQLERRM || '. Transaction rolled back.');
END SafeTransferFunds;
/

-- Test Cases script block (Copy and run to test)
/*
DECLARE
    v_bal1 NUMBER;
    v_bal2 NUMBER;
BEGIN
    -- Initial state print
    SELECT Balance INTO v_bal1 FROM Accounts WHERE AccountID = 1;
    SELECT Balance INTO v_bal2 FROM Accounts WHERE AccountID = 2;
    DBMS_OUTPUT.PUT_LINE('Initial Balance Account 1: $' || v_bal1 || ' | Account 2: $' || v_bal2);
    
    -- Test Case 1: Valid Transfer
    SafeTransferFunds(1, 2, 500);
    
    -- Test Case 2: Insufficient Funds
    SafeTransferFunds(2, 1, 5000);
    
    -- Test Case 3: Invalid Account ID
    SafeTransferFunds(99, 2, 100);
    
    -- Final state print
    SELECT Balance INTO v_bal1 FROM Accounts WHERE AccountID = 1;
    SELECT Balance INTO v_bal2 FROM Accounts WHERE AccountID = 2;
    DBMS_OUTPUT.PUT_LINE('Final Balance Account 1: $' || v_bal1 || ' | Account 2: $' || v_bal2);
END;
/
*/
