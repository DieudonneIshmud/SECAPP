 private void btnStartTestActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        String testName = (String) table.getValueAt(table.getSelectedRow(), 0);
        String duration = (String) table.getValueAt(table.getSelectedRow(), 3);
        duration += ":00";
        Bson bsonFilter = Filters.eq("Title", testName);
        FindIterable<Document> findIt = documents.filter(bsonFilter);
        System.out.println("Result is: " + findIt.first());
        Home.pending_Tests.startCountdown(duration, findIt.first());
        this.setVisible(false);
//        Home.dispose();
//        this.setUndecorated(true);
        Home.pending_Tests.setVisible(true);
    } 
	
	
	
	// THIS IS THE LISTENER.
	private ListSelectionListener createSelectionListener() {

        return (ListSelectionEvent e) -> {
//            Object valueAt = table.getValueAt(table.getSelectedRow(), WIDTH);
            Date date = (Date) table.getValueAt(table.getSelectedRow(), 2);// the 3 is the index of the date
            if (date.compareTo(new Date(System.currentTimeMillis())) <= 0) {
                btnStartTest.setEnabled(true);
            } else {
                btnStartTest.setEnabled(false);
            }
//                btnStartTest.setEnabled(true);/*date.compareTo(new Date(System.currentTimeMillis()))<=0);*/
        };
    }