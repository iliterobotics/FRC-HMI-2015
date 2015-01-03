package org.ilite.util.gui.components.tabbedpane;

import org.ilite.util.lang.IUpdate;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class AbstractDynamicTabProvider implements IDynamicTabProvider{
	private IUpdate<AddTabData> mAddTabDataListener = null;

	@Override
	public final JPanel getAddTabPanel() {
		JPanel p = new JPanel(new BorderLayout());
		JButton apply = new JButton("ADD PANEL");
		final AddTabData data = new AddTabData();
		apply.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(mAddTabDataListener != null)
					mAddTabDataListener.update(data);
			}
		});
		p.add(getAddTabPanelImpl(), BorderLayout.CENTER);
		p.add(apply, BorderLayout.SOUTH);
		return p;
	}

	@Override
	public final void addAddTabDataListener(IUpdate<AddTabData> pListener) {
		mAddTabDataListener = pListener;    		
	}

	protected abstract JPanel getAddTabPanelImpl();
}
